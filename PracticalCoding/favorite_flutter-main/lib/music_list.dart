import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:provider/provider.dart';

class MusicList extends StatelessWidget {
  final List<MusicAlbum> musicList;

  const MusicList({super.key, required this.musicList});

  @override
  Widget build(BuildContext context) {
    var appState = context.watch<MusicAppState>();
    if (musicList.isNotEmpty) {
      return Expanded(
        child: ListView.builder(
          itemCount: musicList.length,
          shrinkWrap: true,
          itemBuilder: (context, index) {
            var album = musicList[index];
            return Card(
              child: ListTile(
                leading: Icon(Icons.library_music_outlined),
                title: Text(musicList[index].collectionName),
                subtitle: Text(musicList[index].artistName),
                trailing: IconButton(
                  onPressed: () => appState.toggleFavorite(album),
                  icon:
                      (appState.favorites.any(
                        (item) => item.collectionId == album.collectionId,
                      )
                      ? Icon(Icons.favorite)
                      : Icon(Icons.favorite_border_outlined)),
                ),
              ),
            );
          },
        ),
      );
    } else {
      return SizedBox(height: 8);
    }
  }
}

class MusicAlbum {
  String collectionId = '';
  String artistName = '';
  String artistViewUrl = '';
  String collectionName = '';
  String collectionViewUrl = '';

  MusicAlbum(
    this.collectionId,
    this.collectionName,
    this.artistName,
    this.artistViewUrl,
    this.collectionViewUrl,
  );

  Map<String, dynamic> toJson() => {
    'collectionId': collectionId,
    'collectionName': collectionName,
    'artistName': artistName,
    'artistViewUrl': artistViewUrl,
    'collectionViewUrl': collectionViewUrl,
  };
}

class MusicAppState extends ChangeNotifier {
  List<MusicAlbum> favorites = [];
  List<MusicAlbum> musicList = [];

  Future<void> getFavorite() async {
    try {
      var result = await http.get(
        // API 호출
        Uri.parse(
          "https://us-central1-favorite-music-5bbc1.cloudfunctions.net/user/lwj/likes",
        ),
      );
      var data = json.decode(result.body); // json 데이터를 dart map으로 디코딩
      favorites = data
          .map<MusicAlbum>(
            (r) => MusicAlbum(
              r['collectionId'].toString(),
              r['collectionName'],
              r['artistName'],
              r['artistViewUrl'],
              r['collectionViewUrl'],
            ),
          )
          .toList();
      notifyListeners();
    } catch (e) {
      print(e.toString());
    }
  }

  void toggleFavorite(MusicAlbum album) async {
    if (favorites.any((item) => album.collectionId == item.collectionId)) {
      var result = await http.delete(
        Uri.parse(
          "https://us-central1-favorite-music-5bbc1.cloudfunctions.net/user/lwj/likes/${album.collectionId}",
        ),
        headers: <String, String>{
          'Content-Type': 'application/json; charset=UTF-8',
        },
      );
      if (!result.body.isEmpty)
        favorites.removeWhere(
          (item) => album.collectionId == item.collectionId,
        );
    } else {
      var result = await http.post(
        Uri.parse(
          "https://us-central1-favorite-music-5bbc1.cloudfunctions.net/user/lwj/likes",
        ),
        headers: <String, String>{
          'Content-Type': 'application/json; charset=UTF-8',
        },
        body: jsonEncode(album.toJson()),
      );
      if (!result.body.isEmpty) favorites.add(album);
    }
    notifyListeners();
  }

  Future<void> musicSearch(String artist) async {
    try {
      var result = await http.get(
        // API 호출
        Uri.parse("https://itunes.apple.com/search?entity=album&term=$artist"),
      );

      var data = json.decode(result.body); // json 데이터를 dart map으로 디코딩
      musicList = data['results']
          .map<MusicAlbum>(
            (r) => MusicAlbum(
              r['collectionId'].toString(),
              r['collectionName'],
              r['artistName'],
              r['artistViewUrl'],
              r['collectionViewUrl'],
            ),
          )
          .toList();
      notifyListeners();
    } catch (e) {
      print(e.toString());
    }
  }
}
