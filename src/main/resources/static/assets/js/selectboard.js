addEventAll();

const checkedRadio = document.querySelector('.box input[type="radio"]:checked').value;

sendRadioValueToServer(checkedRadio);

function addEventAll(){

    let list = document.querySelectorAll('.box input');

    list.forEach(element => {
        element.addEventListener('click',(event)=>{
            const value = event.target.value;
            sendRadioValueToServer(value);
            // alert(value);
        })
    });
}

async function sendRadioValueToServer(boardId) {
    if(boardId !== null){
        const url = `/article/list/${boardId}`;
        // console.log(url);
        await fetch(url);
    }
}
