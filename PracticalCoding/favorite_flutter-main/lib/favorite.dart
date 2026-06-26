import 'package:flutter/material.dart';
import 'package:myapp/music_list.dart';
import 'package:provider/provider.dart';

class FavoritePage extends StatelessWidget {
  FavoritePage({super.key});
  
  @override
  Widget build(BuildContext context) {
    MusicAppState appState = context.watch<MusicAppState>();
    return Padding(
      padding: EdgeInsets.all(8.0),
      child: (appState.favorites.isNotEmpty)
          ? MusicList(musicList: appState.favorites)
          : Center(child: Text("좋아하는 음악앨범이 없습니다.")),
    );
  }
}
