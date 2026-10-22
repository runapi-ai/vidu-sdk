import type { HttpClient, PollingOptions, RequestOptions } from '@runapi.ai/core';
import { compactParams } from '@runapi.ai/core';
import { pollUntilComplete } from '@runapi.ai/core/internal';
import type {
  CompletedReferenceToVideoResponse,
  ReferenceToVideoParams,
  ReferenceToVideoResponse,
  TaskCreateResponse,
} from '../types';

const ENDPOINT = '/api/v1/vidu/reference_to_video';

/** Generate a video using reference images and optional reference audio. */
export class ReferenceToVideo {
  constructor(private readonly http: HttpClient) {}

  /**
   * Create an reference-to-video task and wait until complete.
   * @param params Image-to-video parameters.
   * @param options Per-request and polling overrides.
   * @returns The completed task with videos.
   */
  async run(params: ReferenceToVideoParams, options?: RequestOptions & PollingOptions): Promise<CompletedReferenceToVideoResponse> {
    const { id } = await this.create(params, options);
    const response = await pollUntilComplete<ReferenceToVideoResponse>(() => this.get(id, options), {
      maxWaitMs: options?.maxWaitMs,
      pollIntervalMs: options?.pollIntervalMs,
    });
    return response as CompletedReferenceToVideoResponse;
  }

  /**
   * Create an reference-to-video task; returns immediately with a task id.
   * @param params Image-to-video parameters.
   * @param options Per-request overrides.
   * @returns The task creation result with id.
   */
  async create(params: ReferenceToVideoParams, options?: RequestOptions): Promise<TaskCreateResponse> {
    const body = compactParams(params);
    return this.http.request<TaskCreateResponse>('POST', ENDPOINT, {
      body,
      ...options,
    });
  }

  /**
   * Fetch the current status of an reference-to-video task.
   * @param id The task id.
   * @param options Per-request overrides.
   * @returns The current reference-to-video task status.
   */
  async get(id: string, options?: RequestOptions): Promise<ReferenceToVideoResponse> {
    return this.http.request<ReferenceToVideoResponse>('GET', `${ENDPOINT}/${id}`, options ?? {});
  }
}
