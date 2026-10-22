package ai.runapi.vidu.types;

import com.fasterxml.jackson.annotation.JsonCreator;

/** Model slug for reference to video operations. */
public final class ReferenceToVideoModel extends ViduValue {
  /** vidu-q4-reference-to-video model slug. */
  public static final ReferenceToVideoModel VIDU_Q4_REFERENCE_TO_VIDEO = new ReferenceToVideoModel("vidu-q4-reference-to-video");

  /** Creates a model value from a literal model slug. */
  @JsonCreator
  public ReferenceToVideoModel(String value) {
    super(value);
  }
}
