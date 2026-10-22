package ai.runapi.vidu.types;

import com.fasterxml.jackson.annotation.JsonCreator;

/** Model slug for image to video operations. */
public final class ImageToVideoModel extends ViduValue {
  /** vidu-q4-image-to-video model slug. */
  public static final ImageToVideoModel VIDU_Q4_IMAGE_TO_VIDEO = new ImageToVideoModel("vidu-q4-image-to-video");

  /** Creates a model value from a literal model slug. */
  @JsonCreator
  public ImageToVideoModel(String value) {
    super(value);
  }
}
