/**
 *
 */
$(function() {
	// 大陸名が変更されたら発動
	$('select[name="selectBox1"]').change(function() {
		// 選択されている大陸のクラス名を取得
		var selectBox1Name = $('select[name="selectBox1"] option:selected').attr("class");
		console.log(selectBox1Name);
		// 国名の要素数を取得
		var count = $('select[name="selectBox2"]').children().length;
		// 国名の要素数分、for文で回す
		for (var i=0; i<count; i++) {
			var selectBox2 = $('select[name="selectBox2"] option:eq(' + i + ')');
			if(selectBox2.attr("class") === selectBox1Name) {
				// 選択した大陸と同じクラス名だった場合
				selectBox2.show();
			}else {
				// 選択した大陸とクラス名が違った場合
				if(selectBox2.attr("class") === "msg") {
					// 「国名を選択して下さい」という要素だった場合
						selectBox2.show();  //「国名を選択して下さい」を表示させる
//						selectBox2.prop('selected',true);  //「国名を選択して下さい」を強制的に選択されている状態にする
				} else {
					// 「国名を選択して下さい」という要素でなかった場合
					selectBox2.hide();
				}
			}
		}
	});
});


$(function() {
	// 国名が変更されたら発動
	$('select[name="selectBox2"]').change(function() {
		// 選択されている大陸のクラス名を取得
		var selectBox2Name = $('select[name="selectBox2"] option:selected').attr("value");
//		alert(selectBox2Name);
		console.log(selectBox2Name);
		document.getElementById("fm").submit();
	});
});



