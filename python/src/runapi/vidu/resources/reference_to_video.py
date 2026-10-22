"""Vidu reference-to-video resource."""

from __future__ import annotations

from typing import Any, Optional

from runapi.core import Resource, RequestOptions

from ..types import (
    CompletedReferenceToVideoResponse,
    ReferenceToVideoResponse,
)


class ReferenceToVideo(Resource):
    """Generate videos from a first-frame image with Vidu models."""

    ENDPOINT = "/api/v1/vidu/reference_to_video"

    RESPONSE_CLASS = ReferenceToVideoResponse
    COMPLETED_RESPONSE_CLASS = CompletedReferenceToVideoResponse

    def run(self, options: Optional[RequestOptions] = None, **params: Any) -> Any:
        """Create an reference-to-video task and poll until it completes.

        Args:
            **params: Image-to-video parameters (model, reference_image_urls, ...).

        Returns:
            The completed task with videos.
        """
        task = self.create(options=options, **params)
        return self._poll_until_complete(lambda: self.get(task.id, options=options))

    def create(self, options: Optional[RequestOptions] = None, **params: Any) -> Any:
        """Create an reference-to-video task and return immediately with an ``id``.

        Args:
            **params: Image-to-video parameters (model, reference_image_urls, ...).

        Returns:
            The task creation result with an id.
        """
        compacted = self._compact_params(params)
        return self._request("post", self.ENDPOINT, body=compacted, options=options)

    def get(self, id: str, options: Optional[RequestOptions] = None) -> Any:
        """Fetch the current status of an reference-to-video task.

        Args:
            id: The task id.

        Returns:
            The current task status.
        """
        return self._request("get", f"{self.ENDPOINT}/{id}", options=options)
