import type { AsyncTaskStatus, TaskResponse } from '@runapi.ai/core';
export type ViduImageToVideoModel = 'vidu-q4-image-to-video';
export type ViduReferenceToVideoModel = 'vidu-q4-reference-to-video';
export type ViduOutputResolution = '540p' | '720p' | '1080p' | '2k' | '4k';
export type ViduAspectRatio = '16:9' | '9:16' | '1:1' | '4:3' | '3:4';
export interface ImageToVideoParams {
 model: ViduImageToVideoModel;
 first_frame_image_url: string;
 prompt?: string;
 duration_seconds?: number;
 output_resolution?: ViduOutputResolution;
 audio?: boolean;
 callback_url?: string;
}
export interface ReferenceToVideoParams {
 model: ViduReferenceToVideoModel;
 prompt: string;
 reference_image_urls: string[];
 reference_audio_urls?: string[];
 duration_seconds?: number;
 output_resolution?: ViduOutputResolution;
 audio?: boolean;
 aspect_ratio?: ViduAspectRatio;
 callback_url?: string;
}
export interface TaskCreateResponse { id: string; status?: AsyncTaskStatus; }
export interface Video { url: string; }
export interface ImageToVideoResponse extends TaskResponse {
 id: string; status: AsyncTaskStatus; videos?: Video[]; error?: string; [key: string]: unknown;
}
export type ReferenceToVideoResponse = ImageToVideoResponse;
export type CompletedImageToVideoResponse = ImageToVideoResponse & { status: 'completed'; videos: Video[]; };
export type CompletedReferenceToVideoResponse = CompletedImageToVideoResponse;
