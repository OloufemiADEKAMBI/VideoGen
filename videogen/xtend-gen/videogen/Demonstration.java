package videogen;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Random;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.junit.Assert;
import org.junit.Test;
import org.xtext.example.mydsl.VideoGenStandaloneSetupGenerated;
import org.xtext.example.mydsl.videoGen.AlternativeVideoSeq;
import org.xtext.example.mydsl.videoGen.MandatoryVideoSeq;
import org.xtext.example.mydsl.videoGen.OptionalVideoSeq;
import org.xtext.example.mydsl.videoGen.VideoDescription;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;
import org.xtext.example.mydsl.videoGen.VideoSeq;
import playlist.Mediafile;
import playlist.Playlist;
import playlist.PlaylistFactory;

@SuppressWarnings("all")
public class Demonstration {
  public VideoGeneratorModel loadVideoGenerator(final URI uri) {
    VideoGeneratorModel _xblockexpression = null;
    {
      VideoGenStandaloneSetupGenerated _videoGenStandaloneSetupGenerated = new VideoGenStandaloneSetupGenerated();
      _videoGenStandaloneSetupGenerated.createInjectorAndDoEMFRegistration();
      ResourceSetImpl _resourceSetImpl = new ResourceSetImpl();
      Resource res = _resourceSetImpl.getResource(uri, true);
      EList<EObject> _contents = res.getContents();
      EObject _get = _contents.get(0);
      _xblockexpression = ((VideoGeneratorModel) _get);
    }
    return _xblockexpression;
  }
  
  public void saveVideoGenerator(final URI uri, final VideoGeneratorModel pollS) {
    try {
      ResourceSetImpl _resourceSetImpl = new ResourceSetImpl();
      Resource rs = _resourceSetImpl.createResource(uri);
      EList<EObject> _contents = rs.getContents();
      _contents.add(pollS);
      HashMap<Object, Object> _hashMap = new HashMap<Object, Object>();
      rs.save(_hashMap);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void test1() {
    URI _createURI = URI.createURI("foo2.videogen");
    VideoGeneratorModel videoGen = this.loadVideoGenerator(_createURI);
    Assert.assertNotNull(videoGen);
    EList<VideoSeq> _videoseqs = videoGen.getVideoseqs();
    int _size = _videoseqs.size();
    Assert.assertEquals(7, _size);
    EList<VideoSeq> _videoseqs_1 = videoGen.getVideoseqs();
    final Consumer<VideoSeq> _function = (VideoSeq videoseq) -> {
      if ((videoseq instanceof MandatoryVideoSeq)) {
        final VideoDescription desc = ((MandatoryVideoSeq) videoseq).getDescription();
        String _videoid = desc.getVideoid();
        boolean _isNullOrEmpty = StringExtensions.isNullOrEmpty(_videoid);
        if (_isNullOrEmpty) {
          String _genID = this.genID();
          desc.setVideoid(_genID);
        }
      } else {
        if ((videoseq instanceof OptionalVideoSeq)) {
          final VideoDescription desc_1 = ((OptionalVideoSeq) videoseq).getDescription();
          String _videoid_1 = desc_1.getVideoid();
          boolean _isNullOrEmpty_1 = StringExtensions.isNullOrEmpty(_videoid_1);
          if (_isNullOrEmpty_1) {
            String _genID_1 = this.genID();
            desc_1.setVideoid(_genID_1);
          }
        } else {
          final AlternativeVideoSeq altvid = ((AlternativeVideoSeq) videoseq);
          String _videoid_2 = altvid.getVideoid();
          boolean _isNullOrEmpty_2 = StringExtensions.isNullOrEmpty(_videoid_2);
          if (_isNullOrEmpty_2) {
            String _genID_2 = this.genID();
            altvid.setVideoid(_genID_2);
          }
          EList<VideoDescription> _videodescs = altvid.getVideodescs();
          for (final VideoDescription vdesc : _videodescs) {
            String _videoid_3 = vdesc.getVideoid();
            boolean _isNullOrEmpty_3 = StringExtensions.isNullOrEmpty(_videoid_3);
            if (_isNullOrEmpty_3) {
              String _genID_3 = this.genID();
              vdesc.setVideoid(_genID_3);
            }
          }
        }
      }
    };
    _videoseqs_1.forEach(_function);
    URI _createURI_1 = URI.createURI("foo2bis.xmi");
    this.saveVideoGenerator(_createURI_1, videoGen);
    URI _createURI_2 = URI.createURI("foo2bis.videogen");
    this.saveVideoGenerator(_createURI_2, videoGen);
    this.printToHTML(videoGen);
  }
  
  @Test
  public void test3() {
    try {
      URI _createURI = URI.createURI("foo2.videogen");
      VideoGeneratorModel videoGen = this.loadVideoGenerator(_createURI);
      Assert.assertNotNull(videoGen);
      EList<VideoSeq> _videoseqs = videoGen.getVideoseqs();
      int _size = _videoseqs.size();
      Assert.assertEquals(7, _size);
      final File outputFile = new File("outagain.txt");
      final FileWriter out = new FileWriter(outputFile);
      EList<VideoSeq> _videoseqs_1 = videoGen.getVideoseqs();
      final Consumer<VideoSeq> _function = (VideoSeq videoseq) -> {
        try {
          if ((videoseq instanceof MandatoryVideoSeq)) {
            VideoDescription _description = ((MandatoryVideoSeq) videoseq).getDescription();
            final String chem = _description.getLocation();
            out.write(chem);
            out.write("\n");
          } else {
            if ((videoseq instanceof OptionalVideoSeq)) {
              final Random optional = new Random(2);
              int _nextInt = optional.nextInt();
              boolean _equals = (_nextInt == 1);
              if (_equals) {
                VideoDescription _description_1 = ((OptionalVideoSeq) videoseq).getDescription();
                final String chem_1 = _description_1.getLocation();
                out.write(chem_1);
                out.write("\n");
              }
            } else {
              final AlternativeVideoSeq altvid = ((AlternativeVideoSeq) videoseq);
              EList<VideoDescription> _videodescs = altvid.getVideodescs();
              final int size = _videodescs.size();
              Random _random = new Random();
              EList<VideoDescription> _videodescs_1 = altvid.getVideodescs();
              int _size_1 = _videodescs_1.size();
              final int optional_1 = _random.nextInt(_size_1);
              EList<VideoDescription> _videodescs_2 = altvid.getVideodescs();
              VideoDescription _get = _videodescs_2.get(optional_1);
              String _location = _get.getLocation();
              String _plus = (_location + "\n");
              out.write(_plus);
            }
          }
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      };
      _videoseqs_1.forEach(_function);
      out.close();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void test4() {
    try {
      URI _createURI = URI.createURI("foo2.videogen");
      VideoGeneratorModel videoGen = this.loadVideoGenerator(_createURI);
      final Playlist playlist = PlaylistFactory.eINSTANCE.createPlaylist();
      final File outputFile = new File("outagain.txt");
      final FileWriter out = new FileWriter(outputFile);
      EList<VideoSeq> _videoseqs = videoGen.getVideoseqs();
      final Consumer<VideoSeq> _function = (VideoSeq videoseq) -> {
        try {
          if ((videoseq instanceof MandatoryVideoSeq)) {
            VideoDescription _description = ((MandatoryVideoSeq) videoseq).getDescription();
            final String chem = _description.getLocation();
            out.write(chem);
            out.write("\n");
            final Mediafile mediafile = PlaylistFactory.eINSTANCE.createMediafile();
            mediafile.setLocation(chem);
            EList<Mediafile> _videos = playlist.getVideos();
            _videos.add(mediafile);
          } else {
            if ((videoseq instanceof OptionalVideoSeq)) {
              final Random optional = new Random(1);
              int _nextInt = optional.nextInt();
              boolean _equals = (_nextInt == 1);
              if (_equals) {
                VideoDescription _description_1 = ((OptionalVideoSeq) videoseq).getDescription();
                final String chem_1 = _description_1.getLocation();
                out.write(chem_1);
                out.write("\n");
                final Mediafile mediafile_1 = PlaylistFactory.eINSTANCE.createMediafile();
                mediafile_1.setLocation(chem_1);
                EList<Mediafile> _videos_1 = playlist.getVideos();
                _videos_1.add(mediafile_1);
              }
            } else {
              final AlternativeVideoSeq altvid = ((AlternativeVideoSeq) videoseq);
              EList<VideoDescription> _videodescs = altvid.getVideodescs();
              final int size = _videodescs.size();
              Random _random = new Random();
              EList<VideoDescription> _videodescs_1 = altvid.getVideodescs();
              int _size = _videodescs_1.size();
              final int optional_1 = _random.nextInt(_size);
              EList<VideoDescription> _videodescs_2 = altvid.getVideodescs();
              VideoDescription _get = _videodescs_2.get(optional_1);
              String _location = _get.getLocation();
              String _plus = (_location + "\n");
              out.write(_plus);
              final Mediafile mediafile_2 = PlaylistFactory.eINSTANCE.createMediafile();
              EList<VideoDescription> _videodescs_3 = altvid.getVideodescs();
              VideoDescription _get_1 = _videodescs_3.get(optional_1);
              String _location_1 = _get_1.getLocation();
              mediafile_2.setLocation(_location_1);
              EList<Mediafile> _videos_2 = playlist.getVideos();
              _videos_2.add(mediafile_2);
            }
          }
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      };
      _videoseqs.forEach(_function);
      out.close();
      this.convertPlaylistIntoFormat(playlist, "m3u");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void convertPlaylistIntoFormat(final Playlist p, final String format) {
    EList<Mediafile> _videos = p.getVideos();
    final Consumer<Mediafile> _function = (Mediafile video) -> {
      String _location = video.getLocation();
      String _plus = (_location + ".");
      final String str = (_plus + format);
      video.setLocation(str);
      String _location_1 = video.getLocation();
      String _plus_1 = (_location_1 + "\n");
      System.out.println(_plus_1);
    };
    _videos.forEach(_function);
  }
  
  public void printToHTML(final VideoGeneratorModel videoGen) {
    InputOutput.<String>println("<ul>");
    EList<VideoSeq> _videoseqs = videoGen.getVideoseqs();
    final Consumer<VideoSeq> _function = (VideoSeq videoseq) -> {
      if ((videoseq instanceof MandatoryVideoSeq)) {
        final VideoDescription desc = ((MandatoryVideoSeq) videoseq).getDescription();
        String _videoid = desc.getVideoid();
        boolean _isNullOrEmpty = StringExtensions.isNullOrEmpty(_videoid);
        boolean _not = (!_isNullOrEmpty);
        if (_not) {
          String _videoid_1 = desc.getVideoid();
          String _plus = ("<li>" + _videoid_1);
          String _plus_1 = (_plus + "</li>");
          InputOutput.<String>println(_plus_1);
        }
      } else {
        if ((videoseq instanceof OptionalVideoSeq)) {
          final VideoDescription desc_1 = ((OptionalVideoSeq) videoseq).getDescription();
          String _videoid_2 = desc_1.getVideoid();
          boolean _isNullOrEmpty_1 = StringExtensions.isNullOrEmpty(_videoid_2);
          boolean _not_1 = (!_isNullOrEmpty_1);
          if (_not_1) {
            String _videoid_3 = desc_1.getVideoid();
            String _plus_2 = ("<li>" + _videoid_3);
            String _plus_3 = (_plus_2 + "</li>");
            InputOutput.<String>println(_plus_3);
          }
        } else {
          final AlternativeVideoSeq altvid = ((AlternativeVideoSeq) videoseq);
          String _videoid_4 = altvid.getVideoid();
          boolean _isNullOrEmpty_2 = StringExtensions.isNullOrEmpty(_videoid_4);
          boolean _not_2 = (!_isNullOrEmpty_2);
          if (_not_2) {
            String _videoid_5 = altvid.getVideoid();
            String _plus_4 = ("<li>" + _videoid_5);
            String _plus_5 = (_plus_4 + "</li>");
            InputOutput.<String>println(_plus_5);
          }
          EList<VideoDescription> _videodescs = altvid.getVideodescs();
          int _size = _videodescs.size();
          boolean _greaterThan = (_size > 0);
          if (_greaterThan) {
            InputOutput.<String>println("<ul>");
          }
          EList<VideoDescription> _videodescs_1 = altvid.getVideodescs();
          for (final VideoDescription vdesc : _videodescs_1) {
            String _videoid_6 = vdesc.getVideoid();
            boolean _isNullOrEmpty_3 = StringExtensions.isNullOrEmpty(_videoid_6);
            boolean _not_3 = (!_isNullOrEmpty_3);
            if (_not_3) {
              String _videoid_7 = vdesc.getVideoid();
              String _plus_6 = ("<li>" + _videoid_7);
              String _plus_7 = (_plus_6 + "</li>");
              InputOutput.<String>println(_plus_7);
            }
          }
          EList<VideoDescription> _videodescs_2 = altvid.getVideodescs();
          int _size_1 = _videodescs_2.size();
          boolean _greaterThan_1 = (_size_1 > 0);
          if (_greaterThan_1) {
            InputOutput.<String>println("</ul>");
          }
        }
      }
    };
    _videoseqs.forEach(_function);
    InputOutput.<String>println("</ul>");
  }
  
  private static int i = 0;
  
  public String genID() {
    int _plusPlus = Demonstration.i++;
    return ("v" + Integer.valueOf(_plusPlus));
  }
}
