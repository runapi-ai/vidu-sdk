package ai.runapi.vidu.types;

import ai.runapi.core.types.RunApiValue;

abstract class ViduValue extends RunApiValue {
  ViduValue(String value) {
    super(value);
  }
}
