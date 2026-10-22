package ai.runapi.vidu;
import static org.junit.jupiter.api.Assertions.*;
import ai.runapi.core.http.*;
import ai.runapi.core.json.Json;
import ai.runapi.core.RequestOptions;
import ai.runapi.vidu.types.*;
import java.util.*;
import java.io.ByteArrayOutputStream;
import java.time.Duration;
import org.junit.jupiter.api.Test;
class ViduClientTest {
 static class Transport implements HttpTransport {
 List<HttpRequest> requests=new ArrayList<>();
 public HttpResponse send(HttpRequest r) {requests.add(r);return new HttpResponse(200,r.getMethod()==HttpMethod.POST ? "{\"id\":\"task_1\"}" : "{\"id\":\"task_1\",\"status\":\"completed\",\"videos\":[{\"url\":\"https://cdn.runapi.ai/public/samples/video.mp4\"}]}",Collections.emptyMap());}
 public void close() {}
 }
 @Test void imageCreateGetRun() throws Exception {
 Transport t=new Transport(); ViduClient c=ViduClient.builder().apiKey("test").transport(t).build();
 ImageToVideoParams p=ImageToVideoParams.builder().model("vidu-q4-image-to-video").firstFrameImageUrl("https://cdn.runapi.ai/public/samples/image.jpg").audio(false).durationSeconds(5).build();
 assertEquals("task_1",c.imageToVideo().create(p).getId());
 assertEquals("completed",c.imageToVideo().get("task_1").getStatus().value());
 assertNotNull(c.imageToVideo().run(p,RequestOptions.builder().pollingInterval(Duration.ofMillis(1)).build()).getVideos());
 check(t,"image_to_video");
 assertEquals("https://cdn.runapi.ai/public/samples/image.jpg",body(t.requests.get(0)).get("first_frame_image_url").asText());
 }
 @Test void referenceCreateGetRun() throws Exception {
 Transport t=new Transport(); ViduClient c=ViduClient.builder().apiKey("test").transport(t).build();
 ReferenceToVideoParams p=ReferenceToVideoParams.builder().model("vidu-q4-reference-to-video").prompt("move").referenceImageUrls(Arrays.asList("https://cdn.runapi.ai/public/samples/image.jpg","https://cdn.runapi.ai/public/samples/image.jpg")).referenceAudioUrls(Arrays.asList("https://cdn.runapi.ai/public/samples/voice.mp3")).audio(false).build();
 assertEquals("task_1",c.referenceToVideo().create(p).getId());
 assertEquals("completed",c.referenceToVideo().get("task_1").getStatus().value());
 assertNotNull(c.referenceToVideo().run(p,RequestOptions.builder().pollingInterval(Duration.ofMillis(1)).build()).getVideos());
 check(t,"reference_to_video");
 assertEquals("https://cdn.runapi.ai/public/samples/image.jpg",body(t.requests.get(0)).get("reference_image_urls").get(1).asText());
 assertEquals("https://cdn.runapi.ai/public/samples/voice.mp3",body(t.requests.get(0)).get("reference_audio_urls").get(0).asText());
 }
 void check(Transport t,String action) throws Exception {
 assertEquals(HttpMethod.POST,t.requests.get(0).getMethod());assertEquals("/api/v1/vidu/"+action,t.requests.get(0).getPath());
 assertFalse(body(t.requests.get(0)).get("audio").asBoolean());assertEquals(HttpMethod.GET,t.requests.get(1).getMethod());assertEquals("/api/v1/vidu/"+action+"/task_1",t.requests.get(1).getPath());
 }
 static com.fasterxml.jackson.databind.JsonNode body(HttpRequest r) throws Exception {ByteArrayOutputStream o=new ByteArrayOutputStream();r.getBody().writeTo(o);return Json.mapper().readTree(o.toByteArray());}
}
