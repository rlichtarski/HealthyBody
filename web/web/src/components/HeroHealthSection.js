import React, { Component } from 'react';
import styled from 'styled-components';
const stIcon = require('../assets/humanw.png')
const ndIcon = require('../assets/vegan-foodw.png')

class HeroHealthSection extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            
         }
    }
    render() { 
        return ( 
            <GlobalWrapper>
                <LeftPanel>
                    <div className="upper">
                        <div>Stres</div>
                        <div>- jak zapobiegać?</div>

                    </div>
                    <div className="bottom">
                    <div>Stres to uczucie, które powstaje w reakcji na określone zdarzenia. Reakcja na stres mobilizuje organizm do sprostania trudnej sytuacji.
                        <b first onClick={() => window.location = "https://www.medonet.pl/zdrowie,stres---przyczyny--objawy--metody-zapobiegania,artykul,1729062.html" }> Kliknij aby zobaczyć cały artykuł...</b>

                    </div>
                    </div>
                </LeftPanel>
                <CenterPanel>
                    <h1>Z ostatniej chwili..</h1>
                    <CenterItem readOnly big>Andrzej Duda ma koronawirusa -
                    Prezydent czuje się dobrze. Jesteśmy w stałym kontakcie z odpowiednimi służbami medycznymi - poinformował rzecznik prezydencki Błażej Spychalski. Przypomnijmy, że w piątek prezydent Duda był na stadionie PGE Narodowy, gdzie powstaje szpital tymczasowy dla chorych na koronawirusa.</CenterItem>
                    <CenterItem readOnly>Ekspert wypowiedział się na temat koronawirusa : Koniec pandemii koronawirusa w maju lub czerwcu 2021? Najważniejsze – zdaniem eksperta – jest odpowiednie przygotowanie i doposażanie szpitali. „To jest to wąskie gardło systemu” – podkreślił. </CenterItem>
                    <CenterItem readOnly>Tłumy protestowały na ulicach. Pod adresem polityków poleciały niecenzuralne słowa. "Walczymy o siebie!" #pieklokobiet #wyroknakobiety</CenterItem>
                </CenterPanel>
                <RightPanel>
                    <h1>Sprawdź nasze kalkulatory!</h1>
                    <CalcButton first><img src={stIcon}/>Kalkulator BMI</CalcButton>
                    <CalcButton><img src={ndIcon}/>Dzienne spozycie kaloryczne</CalcButton>
                </RightPanel>
            </GlobalWrapper>
         );
    }
}

const LeftPanel = styled.div`
    display: flex;
    flex-direction: column;
    width: 40%;

    b {
        cursor: pointer;
    }

    textarea {
        overflow-y: scroll;
    }

    .upper {
        width: 100%;
        height: 20rem;
        background-color: black;
        display: flex;
        align-items: flex-end;
        font-size: 2.5rem;
        
    }

    .bottom {
        width: 100%;
        height: 5rem;
        background-color: gray;
        color: black;
        font-size: 1.1rem;
    }
`;

const CenterPanel = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: space-between;
    width: 30%;
    margin: 0 2rem;
`

const CenterItem = styled.textarea`
    resize: none;
    background-color: white;
    border-radius: 5px;
    height: ${props => props.big ? '8rem' : '6rem'};
    width: 100%;
    color: black;
    padding: 15px;
    font-size: 1rem;
`

const RightPanel = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    width: 20%;
`

  
const GlobalWrapper = styled.div`
    width: 100%;
    margin-top: 8rem;
    color: white;
    display: flex;
    justify-content: center;
`

const CalcButton = styled.button`
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
    width: 100%;
    height: 10rem;
    margin: 0 0rem;
    color: white;
    font-size: 1.7rem;
    background-color: ${props => props.first ? '#00FF88' : '#FFE854'};
    border: 0;

    img {
        width: 20%;
        margin: 0 0.5rem;
    }
`
 
export default HeroHealthSection;