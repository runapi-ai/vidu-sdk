"""Vidu model lists, enums, and response models."""

from __future__ import annotations

from runapi.core import BaseModel, TaskResponse, optional, required



class MediaUrl(BaseModel):
    url = optional(str)


class VideoResponse(TaskResponse):
    """Response for a video generation task."""

    id = required(str)
    status = optional(str, enum=lambda: TaskResponse.Status.ALL)
    videos = optional([lambda: MediaUrl])
    error = optional(str)


class CompletedVideoResponse(VideoResponse):
    """Narrowed response from ``run()`` once polling observes completion."""

    videos = required([lambda: MediaUrl])


ImageToVideoResponse = VideoResponse
CompletedImageToVideoResponse = CompletedVideoResponse

ReferenceToVideoResponse = VideoResponse
CompletedReferenceToVideoResponse = CompletedVideoResponse
