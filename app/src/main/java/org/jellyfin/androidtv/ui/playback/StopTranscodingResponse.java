package org.jellyfin.androidtv.ui.playback;

import org.jellyfin.androidtv.data.compat.AudioOptions;
import org.jellyfin.androidtv.data.compat.StreamInfo;
import org.jellyfin.androidtv.data.compat.VideoOptions;
import org.jellyfin.apiclient.interaction.ApiClient;
import org.jellyfin.apiclient.interaction.EmptyResponse;
import org.jellyfin.apiclient.interaction.Response;
import org.jellyfin.apiclient.logging.ILogger;

@Deprecated
public class StopTranscodingResponse extends EmptyResponse {
    private PlaybackManager playbackManager;
    private String serverId;
    private StreamInfo currentStreamInfo;
    private AudioOptions options;
    private ILogger logger;
    private Response<StreamInfo> response;
    private Long startPositionTicks;
    private ApiClient apiClient;

    public StopTranscodingResponse(PlaybackManager playbackManager, String serverId, StreamInfo currentStreamInfo, AudioOptions options, ILogger logger, Long startPositionTicks, ApiClient apiClient, Response<StreamInfo> response) {
        this.playbackManager = playbackManager;
        this.serverId = serverId;
        this.currentStreamInfo = currentStreamInfo;
        this.options = options;
        this.logger = logger;
        this.response = response;
        this.startPositionTicks = startPositionTicks;
        this.apiClient = apiClient;
    }

    private void onAny() {
        playbackManager.getVideoStreamInfo(serverId, (VideoOptions) options, startPositionTicks, apiClient, response);
    }

    @Override
    public void onResponse() {
        onAny();
    }

    @Override
    public void onError(Exception ex) {
        logger.error("Error in StopTranscodingProcesses", ex);
        onAny();
    }
}
