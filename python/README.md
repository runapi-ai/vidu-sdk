# Vidu Python SDK for RunAPI

Create image-to-video or reference-to-video tasks, retrieve their status, or use `run` to poll until completion. Validation runs on the RunAPI server.

## Install

```sh
pip install runapi-vidu
```

## Quick start

Set `RUNAPI_API_KEY` in your environment. Replace the example image URL with a publicly accessible reference image.

```python
from runapi.vidu import ViduClient

client = ViduClient()  # reads RUNAPI_API_KEY
result = client.reference_to_video.run(
    model="vidu-q4-reference-to-video",
    prompt="Animate the referenced scene with a slow camera pan",
    reference_image_urls=["https://cdn.runapi.ai/public/samples/image.jpg"],
    audio=False,
)
print(result.videos[0].url)
```

Use `create` to submit without waiting and `get` to retrieve the task by ID. Reference generation requires a prompt and 1–15 image URLs; it also accepts up to 3 MP3 reference audio URLs. Images must be JPG/JPEG/PNG/WEBP, at most 50 MB each; MP3 references must be 3–12 seconds and at most 50 MB each. Remote file properties are checked during provider processing, so a task can fail asynchronously.

See the [API reference](https://runapi.ai/docs/api/vidu/reference-to-video) and [SDK guide](https://runapi.ai/docs/resources/sdks). For current pricing, use the canonical model pages: [image-to-video](https://runapi.ai/models/vidu/q4-image-to-video) and [reference-to-video](https://runapi.ai/models/vidu/q4-reference-to-video).
