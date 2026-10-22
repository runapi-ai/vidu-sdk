# frozen_string_literal: true

module RunApi
  module Vidu
    module Resources
      # Vidu reference-to-video resource.
      # Generate video from reference images, a prompt, and optional reference audio.
      class ReferenceToVideo
        include RunApi::Core::ResourceHelpers

        ENDPOINT = "/api/v1/vidu/reference_to_video"

        RESPONSE_CLASS = Types::ReferenceToVideoResponse
        COMPLETED_RESPONSE_CLASS = Types::CompletedReferenceToVideoResponse

        def initialize(http)
          @http = http
        end

        # Create an reference-to-video task and wait until complete.
        #
        # @param params [Hash] reference-to-video parameters
        # @return [RunApi::Vidu::Types::CompletedReferenceToVideoResponse] completed task with videos
        def run(options: nil, **params)
          task = create(options: options, **params)
          poll_until_complete { get(task.id, options: options) }
        end

        # Create an reference-to-video task.
        #
        # @param params [Hash] reference-to-video parameters
        # @return [RunApi::Vidu::Types::ReferenceToVideoResponse] task creation result with id
        def create(options: nil, **params)
          params = compact_params(params)
          request(:post, ENDPOINT, body: params, options: options)
        end

        # Get reference-to-video task status by task ID.
        #
        # @param id [String] task ID
        # @return [RunApi::Vidu::Types::ReferenceToVideoResponse] current task status
        def get(id, options: nil)
          request(:get, "#{ENDPOINT}/#{id}", options: options)
        end
      end
    end
  end
end
