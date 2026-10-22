"""Vidu client."""

from __future__ import annotations

from typing import Any, Optional

from runapi.core import ProviderClient

from .resources.image_to_video import ImageToVideo
from .resources.reference_to_video import ReferenceToVideo


class ViduClient(ProviderClient):
    """Vidu image-to-video and reference-to-video client."""

    def __init__(self, api_key: Optional[str] = None, **options: Any) -> None:
        super().__init__(api_key, **options)
        http = self._http
        self.image_to_video = ImageToVideo(http)
        self.reference_to_video = ReferenceToVideo(http)
