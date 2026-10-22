package vidu

import "github.com/runapi-ai/core-sdk/go/core"

type ImageToVideoModel string
type ReferenceToVideoModel string
type OutputResolution string
type AspectRatio string
type TaskStatus string

const (
	ModelImageToVideo     ImageToVideoModel     = "vidu-q4-image-to-video"
	ModelReferenceToVideo ReferenceToVideoModel = "vidu-q4-reference-to-video"
)

// AsyncTaskResponse carries the task ID, lifecycle status, and error for all Vidu async operations.
type AsyncTaskResponse struct {
	Usage  *core.TaskUsage `json:"usage,omitempty"`
	ID     string          `json:"id"`
	Status TaskStatus      `json:"status"`
	Error  string          `json:"error,omitempty"`
}

func (r AsyncTaskResponse) GetID() string     { return r.ID }
func (r AsyncTaskResponse) GetStatus() string { return string(r.Status) }
func (r AsyncTaskResponse) GetError() string  { return r.Error }

// Video holds a URL to a generated video file.
type Video struct {
	URL string `json:"url"`
}

// ImageToVideoResponse carries Vidu video task results.
type ImageToVideoResponse struct {
	AsyncTaskResponse
	Videos []Video `json:"videos,omitempty"`
}

type ReferenceToVideoResponse = ImageToVideoResponse
type ImageToVideoParams struct {
	Model              ImageToVideoModel `json:"model" help:"required; public model id"`
	FirstFrameImageURL string            `json:"first_frame_image_url" help:"required; HTTP(S) first-frame image URL"`
	Prompt             string            `json:"prompt,omitempty" help:"prompt; up to 20000 characters"`
	DurationSeconds    *int              `json:"duration_seconds,omitempty" help:"optional; integer 3 to 16 seconds; Default: 5"`
	OutputResolution   OutputResolution  `json:"output_resolution,omitempty" help:"optional; output resolution; Default: 720p"`
	Audio              *bool             `json:"audio,omitempty" help:"optional; generate audio; Default: true"`
	CallbackURL        string            `json:"callback_url,omitempty" help:"optional; completion callback URL"`
}
type ReferenceToVideoParams struct {
	Model              ReferenceToVideoModel `json:"model" help:"required; public model id"`
	Prompt             string                `json:"prompt" help:"prompt; up to 20000 characters"`
	ReferenceImageURLs []string              `json:"reference_image_urls" help:"required; 1 to 15 HTTP(S) reference image URLs"`
	ReferenceAudioURLs []string              `json:"reference_audio_urls,omitempty" help:"optional; at most 3 HTTP(S) MP3 URLs, 3 to 12 seconds each"`
	AspectRatio        AspectRatio           `json:"aspect_ratio,omitempty" help:"optional; output aspect ratio; Default: 16:9"`
	DurationSeconds    *int                  `json:"duration_seconds,omitempty" help:"optional; integer 3 to 16 seconds; Default: 5"`
	OutputResolution   OutputResolution      `json:"output_resolution,omitempty" help:"optional; output resolution; Default: 720p"`
	Audio              *bool                 `json:"audio,omitempty" help:"optional; generate audio; Default: true"`
	CallbackURL        string                `json:"callback_url,omitempty" help:"optional; completion callback URL"`
}
