// Package vidu provides Vidu image and reference video generation.
package vidu

import (
	"context"

	"github.com/runapi-ai/core-sdk/go/base"
	"github.com/runapi-ai/core-sdk/go/core"
	"github.com/runapi-ai/core-sdk/go/option"
)

const (
	imageToVideoPath     = "/api/v1/vidu/image_to_video"
	referenceToVideoPath = "/api/v1/vidu/reference_to_video"
)

// Client provides Vidu video generation.
type Client struct {
	base.Base
	ImageToVideo     *ImageToVideo
	ReferenceToVideo *ReferenceToVideo
}

// NewClient creates a Vidu client with the given options.
func NewClient(opts ...option.ClientOption) (*Client, error) {
	resolved, err := option.ResolveClientOptions(opts...)
	if err != nil {
		return nil, err
	}
	httpClient, err := core.NewHTTPClient(resolved)
	if err != nil {
		return nil, err
	}
	return NewClientWithHTTP(httpClient), nil
}

// NewClientWithHTTP creates a Vidu client with a pre-configured HTTP transport.
func NewClientWithHTTP(httpClient core.HTTPClient) *Client {
	return &Client{
		Base:             base.New(httpClient),
		ImageToVideo:     &ImageToVideo{http: httpClient},
		ReferenceToVideo: &ReferenceToVideo{http: httpClient},
	}
}

// ImageToVideo animates a still first-frame image into video, guided by an optional text prompt.
type ImageToVideo struct{ http core.HTTPClient }

// Create submits an image-to-video task and returns immediately with a task id.
func (r *ImageToVideo) Create(ctx context.Context, params ImageToVideoParams, opts ...option.RequestOption) (*core.TaskCreateResponse, error) {
	requestOptions, _ := option.ResolveRequestOptions(opts...)
	body := core.CompactParams(params)
	return core.PostJSON[core.TaskCreateResponse](ctx, r.http, imageToVideoPath, body, requestOptions)
}

// Get fetches the current status of an image-to-video task by id.
func (r *ImageToVideo) Get(ctx context.Context, id string, opts ...option.RequestOption) (*ImageToVideoResponse, error) {
	requestOptions, _ := option.ResolveRequestOptions(opts...)
	return core.GetJSON[ImageToVideoResponse](ctx, r.http, core.ResourcePath(imageToVideoPath, id), requestOptions)
}

// Run submits an image-to-video task and polls until it completes.
func (r *ImageToVideo) Run(ctx context.Context, params ImageToVideoParams, opts ...option.RequestOption) (*ImageToVideoResponse, error) {
	_, pollingOptions := option.ResolveRequestOptions(opts...)
	return core.RunAsync(ctx, func(ctx context.Context) (*core.TaskCreateResponse, error) { return r.Create(ctx, params, opts...) }, func(ctx context.Context, id string) (*ImageToVideoResponse, error) { return r.Get(ctx, id, opts...) }, pollingOptions)
}

// ReferenceToVideo generates video using reference images and optional audio.
type ReferenceToVideo struct{ http core.HTTPClient }

// Create submits an reference-to-video task and returns immediately with a task id.
func (r *ReferenceToVideo) Create(ctx context.Context, params ReferenceToVideoParams, opts ...option.RequestOption) (*core.TaskCreateResponse, error) {
	requestOptions, _ := option.ResolveRequestOptions(opts...)
	body := core.CompactParams(params)
	return core.PostJSON[core.TaskCreateResponse](ctx, r.http, referenceToVideoPath, body, requestOptions)
}

// Get fetches the current status of an reference-to-video task by id.
func (r *ReferenceToVideo) Get(ctx context.Context, id string, opts ...option.RequestOption) (*ReferenceToVideoResponse, error) {
	requestOptions, _ := option.ResolveRequestOptions(opts...)
	return core.GetJSON[ReferenceToVideoResponse](ctx, r.http, core.ResourcePath(referenceToVideoPath, id), requestOptions)
}

// Run submits an reference-to-video task and polls until it completes.
func (r *ReferenceToVideo) Run(ctx context.Context, params ReferenceToVideoParams, opts ...option.RequestOption) (*ReferenceToVideoResponse, error) {
	_, pollingOptions := option.ResolveRequestOptions(opts...)
	return core.RunAsync(ctx, func(ctx context.Context) (*core.TaskCreateResponse, error) { return r.Create(ctx, params, opts...) }, func(ctx context.Context, id string) (*ReferenceToVideoResponse, error) {
		return r.Get(ctx, id, opts...)
	}, pollingOptions)
}
