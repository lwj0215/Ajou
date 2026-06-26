import 'package:flutter/material.dart';
import 'package:myapp/music_list.dart';
import 'package:provider/provider.dart';

class MusicSearchPage extends StatefulWidget {
  const MusicSearchPage({super.key});

  @override
  State<MusicSearchPage> createState() => _MusicSearchPage();
}

class _MusicSearchPage extends State<MusicSearchPage> {
  late TextEditingController _controller;

  @override
  void initState() {
    super.initState();
    _controller = TextEditingController();
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    MusicAppState appState = context.watch<MusicAppState>();
    return Padding(
      padding: EdgeInsets.all(8.0),
      child: Column(
        children: <Widget>[
          TextField(
            onSubmitted: (String value) => appState.musicSearch(value),
            decoration: InputDecoration(
              border: OutlineInputBorder(),
              labelText: 'Artist Name',
            ),
            controller: _controller,
          ),
          SizedBox(height: 16),
          MusicList(musicList: appState.musicList),
        ],
      ),
    );
  }
}
