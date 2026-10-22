import pytest
from runapi.vidu import ViduClient

class FakeHttp:
    def __init__(self, *responses):
        self.responses = list(responses)
        self.calls = []
    def request(self, method, path, body=None, options=None):
        self.calls.append((method, path, body))
        return self.responses.pop(0)

@pytest.mark.parametrize("resource,fields", [
    ("image_to_video", {"first_frame_image_url": "https://x/image.jpg"}),
    ("reference_to_video", {"reference_image_urls": ["https://x/1.jpg", "https://x/2.jpg"], "reference_audio_urls": ["https://x/a.mp3"], "aspect_ratio": "16:9"}),
])
def test_create_get_and_run(resource, fields):
    completed = {"id": "t1", "status": "completed", "videos": [{"url": "https://x/v.mp4"}]}
    fake = FakeHttp({"id": "t1", "status": "pending"}, completed, {"id": "t1", "status": "pending"}, completed)
    client = ViduClient(api_key="test", http_client=fake)
    api = getattr(client, resource)
    params = dict(model="vidu-q4-" + resource.replace("_", "-"), prompt="animate", duration_seconds=5, output_resolution="720p", audio=False, callback_url="https://x/callback", **fields)
    assert api.create(**params).id == "t1"
    assert fake.calls[0] == ("post", "/api/v1/vidu/" + resource, params)
    assert api.get("t1").videos[0].url == "https://x/v.mp4"
    assert fake.calls[1] == ("get", "/api/v1/vidu/" + resource + "/t1", None)
    assert api.run(**params).videos[0].url == "https://x/v.mp4"
