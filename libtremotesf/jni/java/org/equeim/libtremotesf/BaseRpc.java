/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.equeim.libtremotesf;

public class BaseRpc {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected BaseRpc(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(BaseRpc obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        libtremotesfJNI.delete_BaseRpc(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public ServerSettings serverSettings() {
    long cPtr = libtremotesfJNI.BaseRpc_serverSettings(swigCPtr, this);
    return (cPtr == 0) ? null : new ServerSettings(cPtr, false);
  }

  public ServerStats serverStats() {
    long cPtr = libtremotesfJNI.BaseRpc_serverStats(swigCPtr, this);
    return (cPtr == 0) ? null : new ServerStats(cPtr, false);
  }

  public TorrentsVector torrents() {
    return new TorrentsVector(libtremotesfJNI.BaseRpc_torrents(swigCPtr, this), false);
  }

  public Torrent torrentByHash(String hash) {
    long cPtr = libtremotesfJNI.BaseRpc_torrentByHash(swigCPtr, this, hash);
    return (cPtr == 0) ? null : new Torrent(cPtr, true);
  }

  public Torrent torrentById(int id) {
    long cPtr = libtremotesfJNI.BaseRpc_torrentById(swigCPtr, this, id);
    return (cPtr == 0) ? null : new Torrent(cPtr, true);
  }

  public boolean isConnected() {
    return libtremotesfJNI.BaseRpc_isConnected(swigCPtr, this);
  }

  public int status() {
    return libtremotesfJNI.BaseRpc_status(swigCPtr, this);
  }

  public int error() {
    return libtremotesfJNI.BaseRpc_error(swigCPtr, this);
  }

  public boolean isLocal() {
    return libtremotesfJNI.BaseRpc_isLocal(swigCPtr, this);
  }

  public int torrentsCount() {
    return libtremotesfJNI.BaseRpc_torrentsCount(swigCPtr, this);
  }

  public boolean backgroundUpdate() {
    return libtremotesfJNI.BaseRpc_backgroundUpdate(swigCPtr, this);
  }

  public void setBackgroundUpdate(boolean background) {
    libtremotesfJNI.BaseRpc_setBackgroundUpdate(swigCPtr, this, background);
  }

  public boolean isUpdateDisabled() {
    return libtremotesfJNI.BaseRpc_isUpdateDisabled(swigCPtr, this);
  }

  public void setUpdateDisabled(boolean disabled) {
    libtremotesfJNI.BaseRpc_setUpdateDisabled(swigCPtr, this, disabled);
  }

  public void resetServer() {
    libtremotesfJNI.BaseRpc_resetServer(swigCPtr, this);
  }

  public void connect() {
    libtremotesfJNI.BaseRpc_connect(swigCPtr, this);
  }

  public void disconnect() {
    libtremotesfJNI.BaseRpc_disconnect(swigCPtr, this);
  }

  public void addTorrentFile(byte[] fileData, String downloadDirectory, int[] wantedFiles, int[] unwantedFiles, int[] highPriorityFiles, int[] normalPriorityFiles, int[] lowPriorityFiles, int bandwidthPriority, boolean start) {
    libtremotesfJNI.BaseRpc_addTorrentFile(swigCPtr, this, fileData, downloadDirectory, wantedFiles, unwantedFiles, highPriorityFiles, normalPriorityFiles, lowPriorityFiles, bandwidthPriority, start);
  }

  public void addTorrentLink(String link, String downloadDirectory, int bandwidthPriority, boolean start) {
    libtremotesfJNI.BaseRpc_addTorrentLink(swigCPtr, this, link, downloadDirectory, bandwidthPriority, start);
  }

  public void startTorrents(int[] ids) {
    libtremotesfJNI.BaseRpc_startTorrents(swigCPtr, this, ids);
  }

  public void startTorrentsNow(int[] ids) {
    libtremotesfJNI.BaseRpc_startTorrentsNow(swigCPtr, this, ids);
  }

  public void pauseTorrents(int[] ids) {
    libtremotesfJNI.BaseRpc_pauseTorrents(swigCPtr, this, ids);
  }

  public void removeTorrents(int[] ids, boolean deleteFiles) {
    libtremotesfJNI.BaseRpc_removeTorrents(swigCPtr, this, ids, deleteFiles);
  }

  public void checkTorrents(int[] ids) {
    libtremotesfJNI.BaseRpc_checkTorrents(swigCPtr, this, ids);
  }

  public void moveTorrentsToTop(int[] ids) {
    libtremotesfJNI.BaseRpc_moveTorrentsToTop(swigCPtr, this, ids);
  }

  public void moveTorrentsUp(int[] ids) {
    libtremotesfJNI.BaseRpc_moveTorrentsUp(swigCPtr, this, ids);
  }

  public void moveTorrentsDown(int[] ids) {
    libtremotesfJNI.BaseRpc_moveTorrentsDown(swigCPtr, this, ids);
  }

  public void moveTorrentsToBottom(int[] ids) {
    libtremotesfJNI.BaseRpc_moveTorrentsToBottom(swigCPtr, this, ids);
  }

  public void reannounceTorrents(int[] ids) {
    libtremotesfJNI.BaseRpc_reannounceTorrents(swigCPtr, this, ids);
  }

  public void setTorrentsLocation(int[] ids, String location, boolean moveFiles) {
    libtremotesfJNI.BaseRpc_setTorrentsLocation(swigCPtr, this, ids, location, moveFiles);
  }

  public void getTorrentFiles(int id, boolean scheduled) {
    libtremotesfJNI.BaseRpc_getTorrentFiles(swigCPtr, this, id, scheduled);
  }

  public void getTorrentPeers(int id, boolean scheduled) {
    libtremotesfJNI.BaseRpc_getTorrentPeers(swigCPtr, this, id, scheduled);
  }

  public void renameTorrentFile(int torrentId, String filePath, String newName) {
    libtremotesfJNI.BaseRpc_renameTorrentFile(swigCPtr, this, torrentId, filePath, newName);
  }

  public void getDownloadDirFreeSpace() {
    libtremotesfJNI.BaseRpc_getDownloadDirFreeSpace(swigCPtr, this);
  }

  public void getFreeSpaceForPath(String path) {
    libtremotesfJNI.BaseRpc_getFreeSpaceForPath(swigCPtr, this, path);
  }

  public final static class Status {
    public final static int Disconnected = 0;
    public final static int Connecting = Disconnected + 1;
    public final static int Connected = Connecting + 1;
  }

  public final static class Error {
    public final static int NoError = 0;
    public final static int TimedOut = NoError + 1;
    public final static int ConnectionError = TimedOut + 1;
    public final static int AuthenticationError = ConnectionError + 1;
    public final static int ParseError = AuthenticationError + 1;
    public final static int ServerIsTooNew = ParseError + 1;
    public final static int ServerIsTooOld = ServerIsTooNew + 1;
  }

}
