window.addEventListener("load", function(){
    var section = document.querySelector("#s11");
    var button1 = section.querySelector(".button1");
	var tbody = section.querySelector("tbody");    
	var pager = section.querySelector(".pager");

	pager.onclick = function(e){
		e.preventDefault();		
		var page = parseInt(e.target.innerText);
		console.log(page);
		
		load(page);
	}

    button1.onclick = function(){   
        load();
    }

	function load(page){				
		
		if(page == undefined)
			page = 1;
				
		var request = new XMLHttpRequest();
		//request.onreadystatechange = function(){
		request.onload = function(){
            //if(request.readyState == 4){
	
				tbody.innerHTML = "";
	
				var notices = JSON.parse(request.responseText);				
				
				for(var i=0; i<notices.length; i++){
					var n = notices[i];
					var tr = '<tr> \
								<td>'+n.id+'</td> \
								<td><a href="detail.html">'+n.title+'</a></td> \
								<td>'+n.writerId+'</td> \
								<td> \
									2019-08-18 \
								</td> \
								<td>146</td> \
								<td><a href="">수정</a><a href="">삭제</a></td> \
							</tr>';
							
					tbody.insertAdjacentHTML('beforeend', tr);
				//}
				//tobdy.appendChild(node Object)
				//tbody.append("textnode")					
			}            	
        };
        request.open("GET", "/api/board/notice/list?p="+page, true);
        request.send();
	}
});
//------- s10 마우스 이벤트 with 갤러리 --------------------------------
window.addEventListener("load", function(){
    var section = document.querySelector("#s10");
    var preButton = section.querySelector(".prev-button");
    var nextButton = section.querySelector(".next-button");
    var ul = section.querySelector("ul");
    var showRoom = section.querySelector(".show-room");
    var showRoomImg = showRoom.firstElementChild;    
    var current = ul.querySelector("li:nth-child(4)");    
    var index = 0;

    showRoom.style.transition = "500ms";
    
    showRoom.addEventListener("click", function(e){
        //if(조건에 맞으면)
        //    e.stopPropagation();

        console.log("img의 부모 clicked");
        showRoomImg.style.transform = "scale(1.2,1.2)";
    }, true);
    
    showRoomImg.onclick = function(e){
        //e.stopPropagation();

        console.log("img clicked");
        //showRoomImg.style.width = ""
        e.target.style.transform = "scale(1.7,1.7)";
    };

    // 이벤트 버블링에 대한 문제와 해결 방법
    // showRoom.style.transition = "500ms";
    
    // showRoom.onclick = function(e){
    //     console.log("img의 부모 clicked");
    //     showRoomImg.style.transform = "scale(1,1.2)";
    // };

    // showRoomImg.onclick = function(e){
    //     e.stopPropagation();

    //     console.log("img clicked");
    //     //showRoomImg.style.width = ""
    //     e.target.style.transform = "scale(1.2,1.2)";
    // };

    // 3. 하수를 면하는 방법
    // ul>li>img
    ul.onclick = function(e){      
        if(e.target.tagName != "IMG")
            return;
        
        current.classList.remove("current");            
        current = e.target.parentElement;
        current.classList.add("current");        
        // 현재 선택된 녀석
        var newImg = current.firstElementChild;
        showRoomImg.src = newImg.src;
    };
    //---------------------------------------------
    // 2. 조금 개선한 방법
    // var imgClickHandler = function(e){            
    //     current.classList.remove("current");            
    //     current = e.target.parentElement;
    //     current.classList.add("current");        
    //     // 현재 선택된 녀석
    //     var newImg = current.firstElementChild;
    //     showRoomImg.src = newImg.src;
    // };

    // var imgs = section.querySelectorAll("ul img");
    // for(var i=0; i<imgs.length; i++)
    //     imgs[i].onclick = imgClickHandler;

    // console.log(imgs[0].onclick === imgs[1].onclick);
    //---------------------------------------------
    // 1. 가장 복잡하고 가장 바람직하지 않은 방법
    // var imgs = section.querySelectorAll("ul img");
    // for(var i=0; i<imgs.length; i++)
    //     imgs[i].onclick = function(e){
            
    //         current.classList.remove("current");            
    //         current = e.target.parentElement;
    //         current.classList.add("current");
            
    //         // 현재 선택된 녀석
    //         var newImg = current.firstElementChild;

    //         showRoomImg.src = newImg.src;
    //     }
    
    preButton.onclick = function(e){
        e.preventDefault();

        if(current.previousElementSibling == null){
            alert("이전 항목이 없습니다.");
            return;
        }
        
        current.classList.remove("current");
        current = current.previousElementSibling;
        current.classList.add("current");
        showRoomImg.src = current.firstElementChild.src;
    };

    nextButton.onclick = function(e){
        e.preventDefault();

        if(current.nextElementSibling == null){
            alert("다음 항목이 없습니다.");
            return;
        }

        index--;
        var x = index*100+"px";
        ul.style.transform = "translateX("+x+")";

        current.classList.remove("current");
        current = current.nextElementSibling;
        current.classList.add("current");
        showRoomImg.src = current.firstElementChild.src;
        // current.style.opacity = "1";
        // current.style.border = "1px solid green";
        // current.style["border-width"] = "2px";
        // current.style.borderColor = "red";
        //opacity: 1;
        //border:1px solid green;
    };
    
});
//------ s9 ------------------------------------------
window.addEventListener("load", function(){
    var section = document.querySelector("#s9");
    var container = section.querySelector(".container");
    var addButton = section.querySelector(".add-button");
    var delButton = section.querySelector(".del-button");
    var changeButton = section.querySelector(".change-button");
    var replaceButton = section.querySelector(".replace-button");
    
    var index = 1;
    
    changeButton.onclick = function(){
        var chks = container.querySelectorAll("input[type=checkbox]:checked");
        
        for(var i=0; i<chks.length; i++){
            var parent = chks[i].parentElement;
            var textSpan = parent.querySelector(".label");
            textSpan.innerText = "바뀜";
        }
    };

    addButton.onclick = function(){
        var item = '<span class="item"> \
                        <input type="checkbox"> \
                        <span class="label">안녕'+(index++)+'</span> \
                    </span>';

        container.insertAdjacentHTML('beforeend', item);


        // 대안 1 : innerHTML 속성 사용하기
        // - 꼼수로 해결한 방식

        // - 문제있는 방식
        // var item = '<span class="item"> \
        //                 <input type="checkbox"> \
        //                 <span>안녕'+(index++)+'</span> \
        //             </span>';

        // container.innerHTML += item;

        // 노드 객체를 직접 생성한 방법들..
        //=====================================
        //1. span 엘리먼트 객체 생성
        //var span = document.createElement("span");        
        ////2. 텍스트 노드 생성
        //var text = document.createTextNode("안녕!"+index++);
        ////3. span에 2번에서 생성한 text 객체를 추가하고
        //span.append("안녕!"+index++);
        //4. span 객체를 container에 추가한다.
        //container.append(span);

        //======= 이전 기능으로 구현 =============
        //1. span 엘리먼트 객체 생성
        //var span = document.createElement("span");        
        //2. 텍스트 노드 생성
        //var text = document.createTextNode("안녕!"+index++);
        //3. span에 2번에서 생성한 text 객체를 추가하고
        //span.appendChild(text);
        //4. span 객체를 container에 추가한다.
        //container.appendChild(span);
        //------------------------------
        // //1. 텍스트 노드 생성
        // var text = document.createTextNode("안녕!");
        // //2. 컨테이너에 노드 추가
        // container.appendChild(text);
    };

    delButton.onclick = function(){

        //==== 선택 삭제 방식을 통한 구현
        //- 선택된 노드 찾기
        //- 보다 바람직한 방법 : 슈도 클래스를 이용하기
        var chks = container.querySelectorAll("input[type=checkbox]:checked");
        
        for(var i=0; i<chks.length; i++)
            chks[i].parentElement.remove();

        //- 바람직하지 않은 방법 : 직접 모든 노드를 순회하면서 검사하기
        /*
        // 1. item 목록을 얻는다.
        var items = container.children;
        // 2. item을 반복적으로 순회하면서 checkbox를 얻는다.
        // for(var i=0; i<items.length; i++){
        //     var checkbox = items[i].querySelector("input[type=checkbox]");
        // }
        // 3. checkbox의 상태가 선택되어 있는 것들을 추려서 array에 담는다.
        var chks = [];
        for(var i=0; i<items.length; i++){
            var checkbox = items[i].querySelector("input[type=checkbox]");
            if(checkbox.checked)
                chks.push(checkbox);
        }

        // 4. console.log에 chks를 출력해보자.
        console.log(chks);
        */

        //==== 기본 기능 ===========================
        //- 새로운 기능으로 구현
        //container.lastElementChild.remove();
        //- 이전 기능으로 구현
        //container.removeChild(container.lastElementChild);
    };

    replaceButton.onclick = function(){
        //====== 선택된 항목 교체하기
        var chks = container.querySelectorAll("input[type=checkbox]:checked");
        
        if(chks.length != 2){
            alert("2개가 선택되어야만 합니다.");
            return;
        }

        var item1 = chks[0].parentElement;
        var item2 = chks[1].parentElement;
        var before = item2.previousElementSibling;

        //container.replaceChild(newOne, oldOne);
        item1.replaceWith(item2);
        before.insertAdjacentElement('afterend', item1);


        //====== 새로운 기능으로 구현
        // var oldOne = container.children[1];
        // var newOne = container.children[2];
        // container.replaceChild(newOne, oldOne);
        // newOne.insertAdjacentElement('afterend', oldOne);
        
        //newOne.insertAdjacentHTML();
        //newOne.insertAdjacentText();
        //======= 이전 기능으로 구현
        // ---- 두번째<->세번째 교환
        // var oldOne = container.children[1];
        // var newOne = container.children[2];
        // container.replaceChild(newOne, oldOne); //oldOne은 사라진다.
        // container.insertBefore(oldOne,newOne.nextElementSibling);
        // -----첫번째<->막내를 교환 ----
        // var oldOne = container.firstElementChild;
        // var newOne = container.lastElementChild;
        // container.replaceChild(newOne, oldOne);
        // container.appendChild(oldOne);
    };
    
});
//------- s8 갤러리 --------------------------------
window.addEventListener("load", function(){
    var section = document.querySelector("#s8");
    var preButton = section.querySelector(".prev-button");
    var nextButton = section.querySelector(".next-button");
    var ul = section.querySelector("ul");
    var showRoomImg = section.querySelector(".show-room").firstElementChild;
    
    var current = ul.querySelector("li:nth-child(4)");
    
    var index = 0;
    
    preButton.onclick = function(){
        if(current.previousElementSibling == null){
            alert("이전 항목이 없습니다.");
            return;
        }
        
        current.classList.remove("current");
        current = current.previousElementSibling;
        current.classList.add("current");
        showRoomImg.src = current.firstElementChild.src;
    };

    nextButton.onclick = function(){
        if(current.nextElementSibling == null){
            alert("다음 항목이 없습니다.");
            return;
        }

        index--;
        var x = index*100+"px";
        ul.style.transform = "translateX("+x+")";

        current.classList.remove("current");
        current = current.nextElementSibling;
        current.classList.add("current");
        showRoomImg.src = current.firstElementChild.src;
        // current.style.opacity = "1";
        // current.style.border = "1px solid green";
        // current.style["border-width"] = "2px";
        // current.style.borderColor = "red";
        //opacity: 1;
        //border:1px solid green;
    };
    
});
//---------------------------------------
window.addEventListener("load", function(){
    var section = document.querySelector("#s7");
    var preButton = section.querySelector(".prev-button");
    var nextButton = section.querySelector(".next-button");
    var ul = section.querySelector("ul");

    //var lis = ul.querySelectorAll("li");
    
    var current = ul.querySelector("li:first-child");
    current.innerText = "하하하";

    preButton.onclick = function(){
        current.innerText = "호호호";
        current = current.previousElementSibling;
    };

    nextButton.onclick = function(){
        //current.nextSibling;
        current = current.nextElementSibling;
        current.innerText = "하하하";
    };
    
});

// ----------------------------------
window.addEventListener("load", function(){
    var section = document.getElementById("s6");
    var preButton = section.getElementsByClassName("prev-button")[0];
    var nextButton = section.getElementsByClassName("next-button")[0];
    var ul = section.getElementsByTagName("ul")[0];

    /*
    모든 노드를 대상으로 순회할 때
    for(var i=0; i<ul.childNodes.length; i++)
        console.log(ul.childNodes[i].nodeType);    
    */
    
    // 엘리먼트 노드를 대상으로 순회할 때
    for(var i=0; i<ul.children.length; i++)
        console.log(ul.children[i].nodeType);    
        
    //var lis = ul.getElementsByTagName("li");

    //var current = ul.firstChild;
    var current = ul.firstElementChild;
    current.innerText = "하하하";

    preButton.onclick = function(){
        current.innerText = "호호호";
        current = current.previousElementSibling;
    };

    nextButton.onclick = function(){
        //current.nextSibling;
        current = current.nextElementSibling;
        current.innerText = "하하하";
    };
    
});

//-----------------------------------------
window.addEventListener("load", function(){
    var section = document.getElementById("s5");
    var urlInput = section.getElementsByClassName("url-input")[0];
    var btn1 = section.getElementsByClassName("button1")[0];
    
    btn1.onclick = function(){
        //location.href=urlInput.value;
        location.replace(urlInput.value);
        //location.reload();
    };
    
});
//--------------------------------------
window.addEventListener("load", function(){
    var section = document.getElementById("s4");
    var btn1 = section.getElementsByClassName("btn1")[0];
    // https://developer.mozilla.org/en-US/docs/Web/API/HTMLIFrameElement/contentWindow
    var win = section.getElementsByTagName("iframe")[0].contentWindow;

    btn1.onclick = function(){
        var btn1InIFrame = win.document.getElementsByTagName("input")[0];
        btn1InIFrame.value = "호호호";
    };
    
});


//--------s3-----------------------------

window.addEventListener("load", function(){
    var section = document.getElementById("s3");
    var searchButton = section.getElementsByClassName("search-button")[0];

    var win;

    searchButton.onclick = function(){
        win = open("ex1-zipcode.html","_blank", "width=300px, height=200px");
        
    };
    
});

// - s2 -----------------------------------------
window.addEventListener("load", function(){
    var section = document.getElementById("s2");
    var countdownButton = section.getElementsByTagName("input")[0];
    var countSpan = section.getElementsByClassName("count-span")[0];
    var count = 60;

    // 객체 초기화
    countSpan.innerText = count;

    countdownButton.onclick = function(){        
        // 카운트 다운 처리
        var timerId = setInterval(function(){
            count--;
            countSpan.innerText = count;

            if(count == 0)
                clearInterval(timerId);
        }, 1000);        
    };    
});

//=========================================
window.addEventListener("load", function(){
    var addButton = document.getElementById("add-button");
    var xInput = document.getElementById("x-input");
    var yInput = document.getElementById("y-input");
    var resultInput = document.getElementById("result-input");

    addButton.onclick = function(){
        
    };
    
});