import React, { Component } from 'react';
import styled from 'styled-components';

class CovidStatistics extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            stats: {
                infected: 0,
                deaths: 0,
                recovered: 0
            }
         }
    }
    
    render() { 

        const { infected, deaths, recovered } = this.state.stats;

        return (
            <GlobalWrapper>
                <h1>Covid Statistics</h1>
                <Wrapper>
                    <Stat>{infected}</Stat>
                    <Stat>{deaths}</Stat>
                    <Stat>{recovered}</Stat>
                </Wrapper>
            </GlobalWrapper>
        );
    }
}

const Stat = styled.div`
    color: white;
    display: flex;
    justify-content: space-evenly;
    margin: 0 3rem;
    width: 10%;
    height: 2rem;
    border: solid 1px white;
    border-radius: 15%;
`
 
const Wrapper = styled.div`
    width: 100%;
    height: 10rem;
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