import React, { Component } from 'react';
import styled from 'styled-components';
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
    
    render() { 

        const { infected, deaths, recovered } = this.state.stats;

        return (
            <GlobalWrapper>
                <Wrapper>
                    <Stat>
                        <img width="10%" height="50%" src={icon1}/> |
                        <div className="statDetails">
                            <h6>Zarażeni (dzisiaj)</h6>
                            <h4 className="infected">{infected}</h4>
                        </div>
                    </Stat>
                    <Stat>
                        <img width="10%" height="50%" src={icon2}/> |
                        <div className="statDetails">
                            <h6>Zgony (dzisiaj)</h6>
                            <h4>{deaths}</h4>
                        </div>
                    </Stat>
                    <Stat>
                        <img width="10%" height="50%" src={icon3}/> |
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

const Stat = styled.div`
    color: white;
    display: flex;
    justify-content: space-evenly;
    align-items: center;
    margin: 0 1.3rem;
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