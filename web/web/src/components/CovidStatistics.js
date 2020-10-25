import React, { Component } from 'react';
import styled from 'styled-components';
import axios from 'axios';
const icon1 = require('../assets/injuredw.png');
const icon2 = require('../assets/deathw.png');
const icon3 = require('../assets/healingw.png');

class CovidStatistics extends Component {
    constructor(props) {
        super(props);
        this.state = {
            stats: {
                infected: '00000',
                deaths: '00000',
                recovered: '00000'
            }
         }
    }

    async componentDidMount() {
        const covidApi = await axios.get('https://corona.lmao.ninja/v2/countries/poland');
        const { todayCases, todayRecovered, todayDeaths} = covidApi.data;
        this.setState({stats: {infected: todayCases, deaths: todayDeaths, recovered: todayRecovered}})
    }
    
    render() { 

        const { infected, deaths, recovered } = this.state.stats;

        return (
            <GlobalWrapper>
                <Wrapper>
                    <Header>Zdrowie</Header>
                    <Stat>
                        <img width="15%" height="50%" src={icon1}/> |
                        <div className="statDetails">
                            <h6>Zarażeni (dzisiaj)</h6>
                            <h4 className="infected">{infected}</h4>
                        </div>
                    </Stat>
                    <Stat>
                        <img width="15%" height="50%" src={icon2}/> |
                        <div className="statDetails">
                            <h6>Zgony (dzisiaj)</h6>
                            <h4>{deaths}</h4>
                        </div>
                    </Stat>
                    <Stat>
                        <img width="15%" height="50%" src={icon3}/> |
                        <div className="statDetails">
                            <h6>Wyleczeni (dzisiaj)</h6>
                            <h4 className="recovered">{recovered}</h4>
                        </div>
                    </Stat>
                </Wrapper>
            </GlobalWrapper>
        );
    }
}

const Header = styled.h1`
    /* color: linear-gradient(to right,#363636, #47d442); */
    background: ${props => props.secondary ? 'white' : 'linear-gradient(to right ,#363636, gray)'};
    margin: 1rem 2rem;
    width: 40%;
    font-size: 5rem;
    font-weight: bold;
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
`

const Stat = styled.div`
    color: white;
    display: flex;
    justify-content: space-evenly;
    align-items: center;
    margin: 0 1rem;
    width: 20rem;
    height: 3.5rem;
    border: solid 0.3rem white;
    border-radius: 10px;

    .infected {
        color: #eb5c54;
    }

    .recovered {
        color: green;
    }

    .statDetails {
        display: flex;
        flex-direction: column;
    }
`
 
const Wrapper = styled.div`
    width: 100%;
    height: 3rem;
    display: flex;
    justify-content: center;
    align-items: center;
`

const GlobalWrapper = styled.div`
    width: 100%;
    display: flex;
    flex-direction: column;
    margin-top: 2rem;
    color: white;
    justify-content: center;
    align-items: center;
`

export default CovidStatistics;