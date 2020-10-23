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
                        <div>elo</div>
                        <div>elo 2</div>

                    </div>
                    <div className="bottom">
                    <div>asdasdasd</div>
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
        display: flex;
        align-items: flex-end;
    }

    .bottom {
        width: 100%;
        height: 5rem;
        background-color: gray;
        color: black;
        font-size: 1.5rem;
    }
`;

const GlobalWrapper = styled.div`
    width: 100%;
    margin-top: 8rem;
    color: white;
    display: flex;
    justify-content: center;
`
 
export default HeroHealthSection;