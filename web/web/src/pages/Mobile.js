import React from 'react';
import Navbar from '../components/Navbar/Navbar';
import styled from 'styled-components';
const bgImg = require('../assets/mobile.jpg');



function Mobile() {
  return (
    <div>
      {/* <Navbar/> */}
        <Img src={bgImg}/>
        <Header target="_blank" href="https://github.com/rradzzio/HealthyBody/tree/main/app">Zobacz naszą mobilną aplikacje! (@ GitHub)</Header>
    </div>
  );
}

const Img = styled.img`
    width: 100%;
    height: 100vh;
`

const Header = styled.a`
    display: flex;
    position: absolute;
    top: 50%;
    right: 0;
    justify-content: center;
    align-items: center;
    color: white;
    background: rgba(0,0,0, 0.5);
    width: 100%;
    font-size: 2rem;
    text-decoration: underline;
    height: 6rem;
`

export default Mobile;
