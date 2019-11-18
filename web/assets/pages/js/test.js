function checkFull() {
    var cks = [];
    var iq = document.getElementById("inputQ");
    var sc = document.getElementById("singleChoice");
    var mc = document.getElementById("multiChoice");
    var cq = document.getElementById("checkQ");

    cks.push(iq);
    cks.push(sc);
    cks.push(mc);
    cks.push(cq);

    var base = ["填空", "单选", "多选", "判断"];

    for(var _ = 0; _ < cks.length; _++){
        var lis = cks[_].getElementsByTagName("li");
        for (var i = 0; i < lis.length; i++){
            var inputs = lis[i].getElementsByTagName("input");
            var flag = false;
            for (var j = 0; j < inputs.length; j++){
                if(_ === 0){
                    var val = inputs[j].value;
                    if(val !== ""){
                        flag = true;
                        break;
                    }
                }else{
                    if (inputs[j].checked === true){
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag){
                alert(base[_] + "第" + (i + 1) + "个未做！");
                return false;
            }
        }
    }

    return true;
}