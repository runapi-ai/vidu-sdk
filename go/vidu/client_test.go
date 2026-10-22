package vidu

import (
	"context"
	"encoding/json"
	"github.com/runapi-ai/core-sdk/go/core"
	"reflect"
	"testing"
)

type stubHTTP struct {
	method, path string
	body         any
}

func (s *stubHTTP) Request(_ context.Context, method, path string, opts *core.HTTPRequestOptions) (json.RawMessage, error) {
	s.method, s.path = method, path
	if opts != nil {
		s.body = opts.Body
	}
	return json.RawMessage(`{"id":"task-1","status":"completed","videos":[{"url":"https://cdn.runapi.ai/public/samples/video.mp4"}]}`), nil
}
func TestImageToVideoCreateAndGet(t *testing.T) {
	s := &stubHTTP{}
	c := NewClientWithHTTP(s)
	audio := false
	result, err := c.ImageToVideo.Create(context.Background(), ImageToVideoParams{Model: ModelImageToVideo, FirstFrameImageURL: "https://cdn.runapi.ai/public/samples/image.jpg", Audio: &audio})
	if err != nil || result.ID != "task-1" {
		t.Fatalf("create: %v %#v", err, result)
	}
	expected := map[string]any{"model": "vidu-q4-image-to-video", "first_frame_image_url": "https://cdn.runapi.ai/public/samples/image.jpg", "audio": false}
	if s.method != "POST" || s.path != "/api/v1/vidu/image_to_video" || !reflect.DeepEqual(s.body, expected) {
		t.Fatalf("request: %s %s %#v", s.method, s.path, s.body)
	}
	task, err := c.ImageToVideo.Get(context.Background(), "task-1")
	if err != nil || s.method != "GET" || s.path != "/api/v1/vidu/image_to_video/task-1" || task.Videos[0].URL != "https://cdn.runapi.ai/public/samples/video.mp4" {
		t.Fatalf("get: %v %#v", err, task)
	}
}
func TestReferenceToVideoCreateAndGet(t *testing.T) {
	s := &stubHTTP{}
	c := NewClientWithHTTP(s)
	audio := false
	_, err := c.ReferenceToVideo.Create(context.Background(), ReferenceToVideoParams{Model: ModelReferenceToVideo, Prompt: "Animate these references", ReferenceImageURLs: []string{"https://cdn.runapi.ai/public/samples/image.jpg", "https://cdn.runapi.ai/public/samples/input.jpg"}, ReferenceAudioURLs: []string{"https://cdn.runapi.ai/public/samples/voice.mp3"}, Audio: &audio})
	expected := map[string]any{"model": "vidu-q4-reference-to-video", "prompt": "Animate these references", "reference_image_urls": []any{"https://cdn.runapi.ai/public/samples/image.jpg", "https://cdn.runapi.ai/public/samples/input.jpg"}, "reference_audio_urls": []any{"https://cdn.runapi.ai/public/samples/voice.mp3"}, "audio": false}
	if err != nil || s.method != "POST" || s.path != "/api/v1/vidu/reference_to_video" || !reflect.DeepEqual(s.body, expected) {
		t.Fatalf("request: %v %s %s %#v", err, s.method, s.path, s.body)
	}
	task, err := c.ReferenceToVideo.Get(context.Background(), "task-1")
	if err != nil || s.method != "GET" || s.path != "/api/v1/vidu/reference_to_video/task-1" || task.Videos[0].URL != "https://cdn.runapi.ai/public/samples/video.mp4" {
		t.Fatalf("get: %v %#v", err, task)
	}
}
