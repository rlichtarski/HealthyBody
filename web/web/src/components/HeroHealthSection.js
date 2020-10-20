import React, { Component } from 'react';
import styled from 'styled-components';

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
                        hej
                    </div>
                    <div className="bottom">
                    </div>
                </LeftPanel>
            </GlobalWrapper>
         );
    }
}

const LeftPanel = styled.div`
    display: flex;
    flex-direction: column;
    width: 40%;

    .upper {
        width: 100%;
        height: 20rem;
        background-color: black;
    }

    .bottom {
        width: 100%;
        height: 5rem;
        background-color: gray;
    }
`;

const GlobalWrapper = styled.div`
    width: 100%;
    margin-top: 2rem;
    color: white;
    display: flex;
    justify-content: center;
`
 
export default HeroHealthSection;