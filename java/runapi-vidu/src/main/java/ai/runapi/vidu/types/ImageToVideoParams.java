package ai.runapi.vidu.types;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Parameters for image to video operations. */
public final class ImageToVideoParams {
  private final String model;
  private final String prompt;
  private final Integer durationSeconds;
  private final String outputResolution;
  private final Boolean audio;
  private final String callbackUrl;
  private final String firstFrameImageUrl;

  private ImageToVideoParams(Builder builder) {
    this.model = builder.model;
    this.prompt = builder.prompt;
    this.durationSeconds = builder.durationSeconds;
    this.outputResolution = builder.outputResolution;
    this.audio = builder.audio;
    this.callbackUrl = builder.callbackUrl;
    this.firstFrameImageUrl = builder.firstFrameImageUrl;
  }

  /** Creates a new ImageToVideoParams builder. */
  public static Builder builder() {
    return new Builder();
  }

  /** Returns the RunAPI action key for this request. */
  public String action() {
    return "vidu/image-to-video";
  }

  /** Converts these parameters to the JSON request body shape. */
  public Map<String, Object> toMap() {
    Map<String, Object> raw = new LinkedHashMap<String, Object>();
    raw.put("model", ViduParamUtils.wireValue(model));
    raw.put("prompt", ViduParamUtils.wireValue(prompt));
    raw.put("duration_seconds", ViduParamUtils.wireValue(durationSeconds));
    raw.put("output_resolution", ViduParamUtils.wireValue(outputResolution));
    raw.put("audio", ViduParamUtils.wireValue(audio));
    raw.put("callback_url", ViduParamUtils.wireValue(callbackUrl));
    raw.put("first_frame_image_url", ViduParamUtils.wireValue(firstFrameImageUrl));
    return ViduParamUtils.compact(raw);
  }

  /** Builder for {@link ImageToVideoParams}. */
  public static final class Builder {
    private String model;
    private String prompt;
    private Integer durationSeconds;
    private String outputResolution;
    private Boolean audio;
    private String callbackUrl;
    private String firstFrameImageUrl;

    private Builder() {}

    /** Sets the model slug using a typed model value. */
    public Builder model(ImageToVideoModel value) {
      this.model = value.value();
      return this;
    }

    /** Sets the model. */
    public Builder model(String value) {
      this.model = value;
      return this;
    }

    /** Sets the prompt. */
    public Builder prompt(String value) {
      this.prompt = value;
      return this;
    }

    /** Sets the duration seconds. */
    public Builder durationSeconds(Integer value) {
      this.durationSeconds = value;
      return this;
    }

    /** Sets the output resolution. */
    public Builder outputResolution(String value) {
      this.outputResolution = value;
      return this;
    }

    /** Sets the audio. */
    public Builder audio(Boolean value) {
      this.audio = value;
      return this;
    }

    /** Sets the callback url. */
    public Builder callbackUrl(String value) {
      this.callbackUrl = value;
      return this;
    }

    /** Sets the first frame image url. */
    public Builder firstFrameImageUrl(String value) {
      this.firstFrameImageUrl = value;
      return this;
    }

    /** Builds immutable image to video parameters. */
    public ImageToVideoParams build() {
      return new ImageToVideoParams(this);
    }
  }
}
