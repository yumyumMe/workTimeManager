package com.example.demo.model;

public class workModel {

	// 作業区分変換関数
	public String translateWorkKbn(int workKbn) {

		String translatedWorkKbn = "";

		if(workKbn == 1) {

			translatedWorkKbn = "要件定義";

		} else if(workKbn == 2) {

			translatedWorkKbn = "基本設計";

		} else if(workKbn == 3) {

			translatedWorkKbn = "詳細設計";

		} else if(workKbn == 4) {

			translatedWorkKbn = "製造";

		} else if(workKbn == 5) {

			translatedWorkKbn = "テスト";

		} else if(workKbn == 6) {

			translatedWorkKbn = "公開作業";

		} else if(workKbn == 7) {

			translatedWorkKbn = "レビュー";

		} else if(workKbn == 8)  {

			translatedWorkKbn = "会議";

		} else if(workKbn == 9) {

			translatedWorkKbn = "見積もり";

		} else if(workKbn == 10) {

			translatedWorkKbn = "調査";

		} else if(workKbn == 11) {

			translatedWorkKbn = "その他";

		}

		return translatedWorkKbn;

	}

}