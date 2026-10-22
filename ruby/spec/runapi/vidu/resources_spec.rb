# frozen_string_literal: true

require "spec_helper"

RSpec.describe RunApi::Vidu::Client do
  %i[image_to_video reference_to_video].each do |name|
    it "creates, retrieves and runs #{name} without changing false or arrays" do
      http = instance_double(RunApi::Core::HttpClient)
      client = described_class.new(api_key: "test", http_client: http)
      resource = client.public_send(name)
      params = {model: "vidu-q4-#{name.to_s.tr("_", "-")}", prompt: "animate", duration_seconds: 5, output_resolution: "720p", audio: false, callback_url: "https://x/callback"}
      if name == :image_to_video
        params[:first_frame_image_url] = "https://x/image.jpg"
      else
        params.merge!(reference_image_urls: ["https://x/1.jpg", "https://x/2.jpg"], reference_audio_urls: ["https://x/a.mp3"], aspect_ratio: "16:9")
      end
      endpoint = "/api/v1/vidu/#{name}"
      expect(http).to receive(:request).with(:post, endpoint, body: params).twice.and_return("id" => "t1", "status" => "pending")
      expect(http).to receive(:request).with(:get, "#{endpoint}/t1").twice.and_return("id" => "t1", "status" => "completed", "videos" => [{"url" => "https://x/v.mp4"}])
      expect(resource.create(**params).id).to eq("t1")
      expect(resource.get("t1").videos.first.url).to eq("https://x/v.mp4")
      expect(resource.run(**params).videos.first.url).to eq("https://x/v.mp4")
    end
  end
end
