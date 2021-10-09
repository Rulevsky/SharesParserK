package com.example.sharesparserk.model

import com.google.gson.annotations.SerializedName

class AllStocks(
    @SerializedName("stocks")
    var stocks: Stocks

//    var stocks: List<Stocks>
//    если сделать так то он ругается
//    E/tag: failure: com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_ARRAY but was BEGIN_OBJECT at line 1 column 12 path $.stocks
//    я так понимаю что ругается что ему пришел AllStocks как объект а не как лист
) {
}