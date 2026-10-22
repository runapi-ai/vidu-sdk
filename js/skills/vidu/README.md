# Vidu API Skill for RunAPI

Use this skill to generate Vidu Q4 videos from a first-frame image or ordered reference images, with optional reference audio. Use the RunAPI CLI for one-off generation and an SDK for application integration.

Install the RunAPI CLI, then inspect `runapi vidu --help` and the selected operation's help before submitting a request. Image-to-video uses `vidu-q4-image-to-video`. Reference-to-video uses `vidu-q4-reference-to-video`, a prompt, and reference image URLs.

Remote images must be JPG/JPEG, PNG, or WEBP. The first-frame image is limited to 50 MB. Reference MP3 files must be 3–12 seconds and no larger than 50 MB. The generation provider checks remote file metadata asynchronously.

## Variants

- [API Reference](https://runapi.ai/docs/api/vidu)
- [SDK guide](https://runapi.ai/docs/resources/sdks)
- [Image-to-video Pricing](https://runapi.ai/models/vidu/q4-image-to-video)
- [Reference-to-video Pricing](https://runapi.ai/models/vidu/q4-reference-to-video)

## Install

Use this skill with Codex, Claude Code, Gemini CLI, Cursor, and compatible agents.

[![skills.sh](https://www.skills.sh/b/runapi-ai/vidu)](https://www.skills.sh/runapi-ai/vidu/vidu)
[![ClawHub](https://img.shields.io/badge/ClawHub-vidu-blue)](https://clawhub.ai/runapi-ai/runapi-vidu)

```sh
npx skills add runapi-ai/vidu -g
```

Or paste this prompt to your AI agent:

```text
Install the vidu skill for me:
Clone https://github.com/runapi-ai/vidu
Copy the skills/vidu/ directory into your agent's skill directory.
Read skills/vidu/SKILL.md before generating a video.
```

Licensed under the Apache License 2.0.
