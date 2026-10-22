package ai.runapi.vidu.types;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Parameters for reference to video operations. */
public final class ReferenceToVideoParams {
  private final String model;
  private final String prompt;
  private final Integer durationSeconds;
  private final String outputResolution;
  private final Boolean audio;
  private final String callbackUrl;
  private final List<String> referenceImageUrls;
  private final List<String> referenceAudioUrls;
  private final String aspectRatio;

  private ReferenceToVideoParams(Builder builder) {
    this.model = builder.model;
    this.prompt = builder.prompt;
    this.durationSeconds = builder.durationSeconds;
    this.outputResolution = builder.outputResolution;
    this.audio = builder.audio;
    this.callbackUrl = builder.callbackUrl;
    this.referenceImageUrls = builder.referenceImageUrls;
    this.referenceAudioUrls = builder.referenceAudioUrls;
    this.aspectRatio = builder.aspectRatio;
  }

  /** Creates a new ReferenceToVideoParams builder. */
  public static Builder builder() {
    return new Builder();
  }

  /** Returns the RunAPI action key for this request. */
  public String action() {
    return "vidu/reference-to-video";
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
    raw.put("reference_image_urls", ViduParamUtils.wireValue(referenceImageUrls));
    raw.put("reference_audio_urls", ViduParamUtils.wireValue(referenceAudioUrls));
    raw.put("aspect_ratio", ViduParamUtils.wireValue(aspectRatio));
    return ViduParamUtils.compact(raw);
  }

  /** Builder for {@link ReferenceToVideoParams}. */
  public static final class Builder {
    private String model;
    private String prompt;
    private Integer durationSeconds;
    private String outputResolution;
    private Boolean audio;
    private String callbackUrl;
    private List<String> referenceImageUrls;
    private List<String> referenceAudioUrls;
    private String aspectRatio;

    private Builder() {}

    /** Sets the model slug using a typed model value. */
    public Builder model(ReferenceToVideoModel value) {
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

    /** Sets the reference image urls. */
    public Builder referenceImageUrls(List<String> value) {
      this.referenceImageUrls = value;
      return this;
    }

    /** Sets the reference audio urls. */
    public Builder referenceAudioUrls(List<String> value) {
      this.referenceAudioUrls = value;
      return this;
    }

    /** Sets the aspect ratio. */
    public Builder aspectRatio(String value) {
      this.aspectRatio = value;
      return this;
    }

    /** Builds immutable reference to video parameters. */
    public ReferenceToVideoParams build() {
      return new ReferenceToVideoParams(this);
    }
  }
}
