
    var oTriangle = document.querySelector('.triangle');
    var oLogIn_p = document.querySelector('.logIn_p');
    var oLogIn = document.querySelector('#logIn');

    oTriangle.onmouseover = function()
    {
        oLogIn_p.style.color = '#999999';
        oLogIn.style.display = 'block';
    }

    oTriangle.onmouseout = function()
    {
        oLogIn_p.style.color = '#787878';
        oLogIn.style.display = 'none';
    }

    oLogIn_p.onmouseover = function()
    {
        oLogIn.style.display = 'block';
    }

    oLogIn_p.onmouseout = function()
    {
        oLogIn.style.display = 'none';
    }

    oLogIn.onmouseover = function()
    {
        oLogIn.style.display = 'block';
    }

    oLogIn.onmouseout = function()
    {
        oLogIn.style.display = 'none';
    }

