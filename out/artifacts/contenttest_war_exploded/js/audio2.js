
// 类初始化

$(function(){

	var song = [
		{
			'cover' : 'img/audio/cover2.jpg',
			'src' : 'https://m10.music.126.net/20190226213656/1273ba6591badb4ec58d894e20b16cb5/ymusic/6716/e860/bf4c/4968e7743011dbfe0b3ee9b5a0a630a9.mp3',
			'title' : 'Memories'
		},
		{
			'cover' : 'img/audio/cover.png',
			'src' : 'https://m10.music.126.net/20190226213519/7c28f695d311252a45dc32f8e8dc8606/ymusic/3c2b/815e/ccb3/bb87793b4f081fec854747b06792140e.mp3',
			'title' : 'number one'
		},
		{
			'cover' : 'img/audio/cover1.jpg',
			'src' : 'https://m10.music.126.net/20190226213750/7dcdf4bc19ca3410e1fb4b5d00c1cdeb/ymusic/bdd1/0fdc/3b96/5a5da2b0f5a6110afc88a34e491a2db9.mp3',
			'title' : '大橋のぞみ - 黒ネコのタンゴ'
		},
		{
			'cover' : 'img/audio/cover3.jpg',
			'src' : 'https://m10.music.126.net/20190226213833/2682ba8bb923795c30b26b11ffc64f66/ymusic/ea5a/c961/b34d/7279377d98235f8feddedad7966045b6.mp3',
			'title' : '黒うさP,初音ミク - 千本桜'
		},
		{
			'cover' : 'img/audio/cover4.jpg',
			'src' : 'https://m10.music.126.net/20190226213951/62d4d90affff951e97a4aece62e5c181/ymusic/091a/9011/bdfa/6e56928fd104ee318d8b965bbcbde1b2.mp3',
			'title' : '极乐净土'
		}
	];

	var audioFn = audioPlay({
		song : song,
		autoPlay : false  //是否立即播放第一首，autoPlay为true且song为空，会alert文本提示并退出
	});

	/* 向歌单中添加新曲目，第二个参数true为新增后立即播放该曲目，false则不播放 */
	audioFn.newSong({
		'cover' : 'img/audio/cover5.jpg',
		'src' : 'https://m10.music.126.net/20190226214033/cfda1138e10d0f00ee1ccbaa3217b193/ymusic/9e82/20e4/8580/bd7c90147cf782ea897fc9652c76b458.mp3',
		'title' : 'B.A.A.B'
	},false);

	/* 暂停播放 */
	//audioFn.stopAudio();

	/* 开启播放 */
	//audioFn.playAudio();

	/* 选择歌单中索引为3的曲目(索引是从0开始的)，第二个参数true立即播放该曲目，false则不播放 */
	//audioFn.selectMenu(3,true);

	/* 查看歌单中的曲目 */
	//console.log(audioFn.song);

	/* 当前播放曲目的对象 */
	//console.log(audioFn.audio);
});