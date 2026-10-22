<p align="center">
  <a href="https://runapi.ai"><img src="https://runapi.ai/icon.svg" height="56" alt="RunAPI"></a>
</p>

<h3 align="center">
  <a href="https://github.com/runapi-ai/vidu-sdk">Vidu API SDK for RunAPI</a>
</h3>

<p align="center">
  Vidu API SDKs for JavaScript, Python, Ruby, Go, Java, and PHP on RunAPI.
</p>

[![npm](https://img.shields.io/npm/v/@runapi.ai/vidu)](https://www.npmjs.com/package/@runapi.ai/vidu)
[![PyPI](https://img.shields.io/pypi/v/runapi-vidu)](https://pypi.org/project/runapi-vidu/)
[![RubyGems](https://img.shields.io/gem/v/runapi-vidu)](https://rubygems.org/gems/runapi-vidu)
[![Go Reference](https://pkg.go.dev/badge/github.com/runapi-ai/vidu-sdk/go.svg)](https://pkg.go.dev/github.com/runapi-ai/vidu-sdk/go)
[![License](https://img.shields.io/github/license/runapi-ai/vidu-sdk)](https://github.com/runapi-ai/vidu-sdk/blob/main/LICENSE)

# Vidu Js SDK for RunAPI

Create image-to-video or reference-to-video tasks, retrieve their status, or use `run` to poll until completion. Validation runs on the RunAPI server.

## Install

```sh
npm install @runapi.ai/vidu
```

## Quick start

Set `RUNAPI_API_KEY` in your environment. Replace the example image URL with a publicly accessible reference image.

```typescript
import { ViduClient } from '@runapi.ai/vidu';

const client = new ViduClient(); // reads RUNAPI_API_KEY
const result = await client.referenceToVideo.run({
  model: 'vidu-q4-reference-to-video',
  prompt: 'Animate the referenced scene with a slow camera pan',
  reference_image_urls: ['https://cdn.runapi.ai/public/samples/image.jpg'],
  audio: false,
});
console.log(result.videos);
```

Use `create` to submit without waiting and `get` to retrieve the task by ID. Reference generation requires a prompt and 1–15 image URLs; it also accepts up to 3 MP3 reference audio URLs. Images must be JPG/JPEG/PNG/WEBP, at most 50 MB each; MP3 references must be 3–12 seconds and at most 50 MB each. Remote file properties are checked during provider processing, so a task can fail asynchronously.

See the [API reference](https://runapi.ai/docs/api/vidu/reference-to-video) and [SDK guide](https://runapi.ai/docs/resources/sdks). For current pricing, use the canonical model pages: [image-to-video](https://runapi.ai/models/vidu/q4-image-to-video) and [reference-to-video](https://runapi.ai/models/vidu/q4-reference-to-video).
