/**
 *
 */
//function setArry1(){
////	var array = new Array();
//	var array = [];
//	array[0] = 'りんご';
//	array[1] = 'みかん';
//	array[2] = 'オレンジ';
//	return array;
//}
function setArry1($arr){
	return $arr;
}

function functionName(africa, europe, asia) {
	var select1 = document.forms.formName.selectName1; // 変数select1を宣言
	var select2 = document.forms.formName.selectName2; // 変数select2を宣言

	select2.options.length = 0; // 選択肢の数がそれぞれに異なる場合、これが重要

	if (select1.options[select1.selectedIndex].value == "Asia") {
		for (  var i = 0;  i < asia.length;  i++  ) {
			select2.options[i] = new Option(asia[i]);
		}
	}

	else if (select1.options[select1.selectedIndex].value == "Africa") {
		for (  var i = 0;  i < africa.length;  i++  ) {
			select2.options[i] = new Option(africa[i]);
		}
	}

	else if (select1.options[select1.selectedIndex].value == "Europe") {
		for (  var i = 0;  i < europe.length;  i++  ) {
			select2.options[i] = new Option(europe[i]);
		}
	}
}