import { describe, expect, it, vi } from 'vitest';
import type { HttpClient } from '@runapi.ai/core';
import { ImageToVideo } from '../../src/resources/image-to-video';
import { ReferenceToVideo } from '../../src/resources/reference-to-video';
describe('Vidu resources', () => {
 it('creates and retrieves image tasks preserving audio false', async () => {
  const task = { id: 'task-1', status: 'completed', videos: [{ url: 'https://cdn.runapi.ai/public/samples/video.mp4' }] };
  const http: HttpClient = { request: vi.fn().mockResolvedValue(task) };
  const resource = new ImageToVideo(http);
  const params = { model: 'vidu-q4-image-to-video' as const, first_frame_image_url: 'https://cdn.runapi.ai/public/samples/image.jpg', audio: false };
  expect(await resource.create(params)).toEqual(task);
  expect(http.request).toHaveBeenCalledWith('POST', '/api/v1/vidu/image_to_video', { body: params });
  expect(await resource.get('task-1')).toEqual(task);
  expect(http.request).toHaveBeenCalledWith('GET', '/api/v1/vidu/image_to_video/task-1', {});
 });
 it('creates and retrieves reference tasks preserving ordered arrays', async () => {
  const task = { id: 'task-2', status: 'completed', videos: [{ url: 'https://cdn.runapi.ai/public/samples/video.mp4' }] };
  const http: HttpClient = { request: vi.fn().mockResolvedValue(task) };
  const resource = new ReferenceToVideo(http);
  const params = { model: 'vidu-q4-reference-to-video' as const, prompt: 'Animate references', reference_image_urls: ['https://cdn.runapi.ai/public/samples/image.jpg', 'https://cdn.runapi.ai/public/samples/input.jpg'], reference_audio_urls: ['https://cdn.runapi.ai/public/samples/voice.mp3'], audio: false };
  expect(await resource.create(params)).toEqual(task);
  expect(http.request).toHaveBeenCalledWith('POST', '/api/v1/vidu/reference_to_video', { body: params });
  expect(await resource.get('task-2')).toEqual(task);
  expect(http.request).toHaveBeenCalledWith('GET', '/api/v1/vidu/reference_to_video/task-2', {});
 });
});
