/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.1.0
 *
 * This file is not intended to be easily readable and contains a number of
 * coding conventions designed to improve portability and efficiency. Do not make
 * changes to this file unless you know what you are doing--modify the SWIG
 * interface file instead.
 * ----------------------------------------------------------------------------- */

#ifndef SWIG_libtremotesf_WRAP_H_
#define SWIG_libtremotesf_WRAP_H_

class SwigDirector_JniRpc : public libtremotesf::JniRpc, public Swig::Director {

public:
    void swig_connect_director(JNIEnv *jenv, jobject jself, jclass jcls, bool swig_mem_own, bool weak_global);
    SwigDirector_JniRpc(JNIEnv *jenv);
    virtual ~SwigDirector_JniRpc();
    virtual void onAboutToDisconnect();
    virtual void onConnectionStateChanged(libtremotesf::RpcConnectionState status);
    virtual void onErrorChanged(libtremotesf::RpcError error,QString const &errorMessage,QString const &detailedConnectionErrorMessage);
    virtual void onServerSettingsChanged(libtremotesf::JniServerSettingsData data);
    virtual void onTorrentsUpdated(std::vector< std::pair< int,int > > const &removedIndexRanges,std::vector< libtremotesf::TorrentData > const &changed,std::vector< libtremotesf::TorrentData > const &added);
    virtual void onTorrentFilesUpdated(int torrentId,std::vector< libtremotesf::TorrentFile > const &changed);
    virtual void onTorrentPeersUpdated(int torrentId,std::vector< std::pair< int,int > > const &removedIndexRanges,std::vector< libtremotesf::Peer > const &changed,std::vector< libtremotesf::Peer > const &added);
    virtual void onServerStatsUpdated(long long downloadSpeed,long long uploadSpeed,libtremotesf::SessionStats currentSession,libtremotesf::SessionStats total);
    virtual void onTorrentAdded(int id,QString const &hashString,QString const &name);
    virtual void onTorrentFinished(int id,QString const &hashString,QString const &name);
    virtual void onTorrentAddDuplicate();
    virtual void onTorrentAddError();
    virtual void onTorrentFileRenamed(int torrentId,QString const &filePath,QString const &newName);
    virtual void onGotDownloadDirFreeSpace(long long bytes);
    virtual void onGotFreeSpaceForPath(QString const &path,bool success,long long bytes);
public:
    bool swig_overrides(int n) {
      return (n < 15 ? swig_override[n] : false);
    }
protected:
    Swig::BoolArray<15> swig_override;
};


#endif
