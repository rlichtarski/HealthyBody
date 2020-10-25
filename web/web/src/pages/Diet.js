import React, { useState } from 'react';
import Navbar from '../components/Navbar/Navbar';
import styled from 'styled-components';
const bgImg = require('../assets/fitnesssw.png');
const table1 = require('../assets/tabelka1.png');
const table2 = require('../assets/tabelka2.png');
const table3 = require('../assets/tabelka3.png');
const mruczek = require('../assets/mruczek.png');

function Diet() {
    const [ page, setPage ] = useState(1);
    switch(page) {
        case 1:
            return (
                <div>
                    <Navbar/>
                    <UpperWrapper>
                        <Header>Dieta &amp; Fitness</Header> 
                    </UpperWrapper>
                    <BottomWrapper>
                        <DietButton first onClick={() => setPage(page + 1)}>Stwórz plan ćwiczeń</DietButton>
                        <DietButton onClick={() => window.location = 'https://diety.nfz.gov.pl/local/diet/login.php'}>Zobacz polecane diety</DietButton>
                        <img className="img" src={bgImg}/>
                    </BottomWrapper>
                </div>
            );
        case 2:
            return (
                <div>
                    <Navbar/>
                    <UpperWrapper>
                        <Header secondary>w ćwiczeniach jestem...</Header> 
                    </UpperWrapper>
                    <BottomWrapper>
                        <DietButtonPage2 green onClick={() => setPage(3)}>Początkujący</DietButtonPage2>
                        <DietButtonPage2 onClick={() => setPage(4)}>Średnio zaawansowany</DietButtonPage2>
                        <DietButtonPage2 red onClick={() => setPage(5)}>Zaawansowany</DietButtonPage2>
                        <img className="img" src={bgImg}/>
                    </BottomWrapper>
                </div>
            );
        case 3:
            return (
                <div>
                    <Navbar/>
                    <UpperWrapper className="tablePage">
                        <Header secondary>To jest Mruczek.</Header> 
                        <SubHeader secondary>Mruczek pokaże Ci jakie ćwiczenia
są dla ciebie odpowiednie.</SubHeader> 
                        <CatImg src={mruczek}/>

                    </UpperWrapper>
                    <BottomWrapper> 
                        <TableImg src={table1}/>
                    </BottomWrapper>
                </div>
            );
        case 4:
            return (
                <div>
                    <Navbar/>
                    <UpperWrapper className="tablePage">
                        <Header secondary>To jest Mruczek.</Header> 
                        <SubHeader secondary>Mruczek pokaże Ci jakie ćwiczenia
są dla ciebie odpowiednie.</SubHeader> 
                        <CatImg src={mruczek}/>

                    </UpperWrapper>
                    <BottomWrapper> 
                        <TableImg src={table2}/>
                    </BottomWrapper>
                </div>
            );
        case 5:
            return (
                <div>
                    <Navbar/>
                    <UpperWrapper className="tablePage">
                        <Header secondary>To jest Mruczek.</Header> 
                        <SubHeader secondary>Mruczek pokaże Ci jakie ćwiczenia
są dla ciebie odpowiednie.</SubHeader> 
                        <CatImg src={mruczek}/>

                    </UpperWrapper>
                    <BottomWrapper> 
                        <TableImg src={table3}/>
                    </BottomWrapper>
                </div>
            );
        default:
            return (
                <div>
                    {/* <Navbar/> */}
                    {/* <h1>odswiez strone</h1> */}
                </div>
            );
    }
}

const UpperWrapper = styled.div`
    display: flex;
    justify-content: flex-start;
    align-items: flex-start;
    width: 100%;
    /* height: 7rem; */
    flex-direction: column;

    .tablePage {
    }
`

const CatImg = styled.img`
    width: auto;
    margin: 2rem;
    height: auto;
    z-index: -1;
`

const BottomWrapper = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    overflow: hidden;
    width: 100%;
    height: 40rem;

    .img {
        position: absolute;
        z-index: -1;
        opacity: 0.3;
        bottom: -0;
        right: 0;
        /* width: 20rem; */
        /* height: 30rem; */
    }
`

const TableImg = styled.img`
    position: absolute;
    z-index: -1;
    bottom: 10rem;
    right: 2rem;
    width: 50rem;
    height: auto;
`

const Header = styled.h1`
    /* color: linear-gradient(to right,#363636, #47d442); */
    background: ${props => props.secondary ? 'white' : 'linear-gradient(to right ,#363636, gray)'};
    margin: 1rem 2rem;
    font-size: 5rem;
    font-weight: bold;
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
`
const SubHeader = styled.h2`
    /* color: linear-gradient(to right,#363636, #47d442); */
    background: ${props => props.secondary ? 'white' : 'linear-gradient(to right ,#363636, gray)'};
    margin: 0 2rem;
    font-size: 1.5rem;
    font-weight: bold;
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
`

const DietButton = styled.button`
    cursor: pointer;
    width: 40rem;
    height: 6rem;
    margin: 1rem;
    color: white;
    font-size: 2rem;
    background-color: ${props => props.first ? 'green' : 'orange'};
    border: 0;
    border-radius: 25px;
`

const DietButtonPage2 = styled.button`
    cursor: pointer;
    width: 40rem;
    height: 4rem;
    margin: 1rem;
    color: white;
    background-color: ${props => props.green ? 'green' : props.red ? 'red' : 'orange'};
    font-size: 2rem;
    border: 0;
    border-radius: 25px;

`

export default Diet;