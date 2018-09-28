/**
 *
 */
function setArry1(){
//	var array = new Array();
	var array = [];
	array[0] = 'りんご';
	array[1] = 'みかん';
	array[2] = 'オレンジ';
	return array;
}

function functionName() {
	var select1 = document.forms.formName.selectName1; // 変数select1を宣言
	var select2 = document.forms.formName.selectName2; // 変数select2を宣言

	select2.options.length = 0; // 選択肢の数がそれぞれに異なる場合、これが重要

	if (select1.options[select1.selectedIndex].value == "果物") {
		var array1 = setArry1();
		for (  var i = 0;  i < array1.length;  i++  ) {
			select2.options[i] = new Option(array1[i]);
		}
	}

	else if (select1.options[select1.selectedIndex].value == "野菜") {
		select2.options[0] = new Option("キャベツ");
		select2.options[1] = new Option("きゅうり");
		select2.options[2] = new Option("にんじん");
		select2.options[3] = new Option("たまねぎ");
	}

	else if (select1.options[select1.selectedIndex].value == "肉類") {
		select2.options[0] = new Option("豚肉");
		select2.options[1] = new Option("牛肉");
	}
}