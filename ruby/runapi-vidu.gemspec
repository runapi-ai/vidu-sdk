# frozen_string_literal: true

Dir.chdir(__dir__) do

  Gem::Specification.new do |spec|
    spec.name = "runapi-vidu"
    spec.version = "0.1.0"
    spec.metadata["runapi_slug"] = "vidu"
    spec.authors = ["RunAPI"]
    spec.email = ["contact@runapi.ai"]

    spec.summary = "Vidu Ruby SDK for RunAPI"
    spec.description = "Create image-to-video or reference-to-video tasks, retrieve their status, or use `run` to poll until completion. Validation runs on the RunAPI server."
    spec.homepage = "https://runapi.ai/models/vidu"
    spec.license = "Apache-2.0"
    spec.required_ruby_version = ">= 3.1.0"
    spec.metadata["homepage_uri"] = "https://runapi.ai/models/vidu"
    spec.metadata["documentation_uri"] = "https://github.com/runapi-ai/vidu-sdk/blob/main/ruby/README.md"
    spec.metadata["source_code_uri"] = "https://github.com/runapi-ai/vidu-sdk"
    spec.metadata["bug_tracker_uri"] = "https://github.com/runapi-ai/vidu-sdk/issues"
    spec.metadata["changelog_uri"] = "https://github.com/runapi-ai/vidu-sdk/blob/main/CHANGELOG.md"


    spec.files = Dir.glob("lib/**/*") + %w[LICENSE README.md]
    spec.extra_rdoc_files = ["README.md"]
        spec.require_paths = ["lib"]

    spec.add_dependency "runapi-core", "~> 0.6.1"
  end
end
