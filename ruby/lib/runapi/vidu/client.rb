# frozen_string_literal: true

module RunApi
  module Vidu
    # Vidu image-to-video and reference-to-video API client.
    class Client < RunApi::Core::Client
      # @return [Resources::ImageToVideo] Image-to-video animation from a first-frame image.
      attr_reader :image_to_video, :reference_to_video

      def initialize(api_key: nil, **options)
        super
        @image_to_video = Resources::ImageToVideo.new(http)
        @reference_to_video = Resources::ReferenceToVideo.new(http)
      end
    end
  end
end
