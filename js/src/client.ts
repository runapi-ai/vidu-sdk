import { BaseClient, type ClientOptions } from '@runapi.ai/core';
import { ImageToVideo } from './resources/image-to-video';
import { ReferenceToVideo } from './resources/reference-to-video';
export class ViduClient extends BaseClient {
  public readonly imageToVideo: ImageToVideo;
  public readonly referenceToVideo: ReferenceToVideo;
  constructor(options: ClientOptions = {}) {
    super(options);
    this.imageToVideo = new ImageToVideo(this.http);
    this.referenceToVideo = new ReferenceToVideo(this.http);
  }
}
