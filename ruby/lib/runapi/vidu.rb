# frozen_string_literal: true

require "runapi/core"
require_relative "vidu/types"
require_relative "vidu/resources/image_to_video"
require_relative "vidu/resources/reference_to_video"
require_relative "vidu/client"

module RunApi
  module Vidu
    AuthenticationError = RunApi::Core::AuthenticationError
    RateLimitError = RunApi::Core::RateLimitError
    InsufficientCreditsError = RunApi::Core::InsufficientCreditsError
    NotFoundError = RunApi::Core::NotFoundError
    ValidationError = RunApi::Core::ValidationError
    TaskFailedError = RunApi::Core::TaskFailedError
    TaskTimeoutError = RunApi::Core::TaskTimeoutError
  end
end
