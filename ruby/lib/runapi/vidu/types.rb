# frozen_string_literal: true

module RunApi
  module Vidu
    # Type definitions and constants for Vidu video generation.
    module Types
      # Response models for asynchronous Vidu video tasks.

      # A generated video file with a download URL.
      class MediaUrl < RunApi::Core::BaseModel
        optional :url, String
      end

      class VideoResponse < RunApi::Core::TaskResponse
        required :id, String
        optional :status, String, enum: -> { RunApi::Core::TaskResponse::Status::ALL }
        optional :videos, [-> { MediaUrl }]
        optional :error, String
      end

      # Narrowed response returned by +run()+ once polling observes +status: "completed"+.
      # +videos+ is required so consumers never have to null-check it on a successful task.
      class CompletedVideoResponse < VideoResponse
        required :videos, [-> { MediaUrl }]
      end

      ReferenceToVideoResponse = VideoResponse
      CompletedReferenceToVideoResponse = CompletedVideoResponse
      ImageToVideoResponse = VideoResponse
      CompletedImageToVideoResponse = CompletedVideoResponse
    end
  end
end
