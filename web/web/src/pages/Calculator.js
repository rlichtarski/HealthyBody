import React, { useState } from 'react';
import Navbar from '../components/Navbar/Navbar';
import styled from 'styled-components';
import axios from 'axios';
const bgImg = require('../assets/calculatorw.png')
const stIcon = require('../assets/humanw.png')
const ndIcon = require('../assets/vegan-foodw.png')

function Calculator() {
    const [ page, setPage ] = useState(1);
    const [ BMI, setBMI ] = useState([]);
    const [ weight, setWeight] = useState('');
    const [ height, setHeight] = useState('');
    const [ age, setAge] = useState('');
    const [ sex, setSex] = useState('m');

    const submitHandler = async (e) => {
        const data = {
            "weight": {
            "value": weight,
            "unit": "kg"
            },
            "height": {
                "value": height,
                "unit": "cm"
            },
            "sex": sex,
            "age": age,
        }
        e.preventDefault();
        const bmiResponse = await axios.post('https://bmi.p.rapidapi.com/', data, {
            headers: {
                'x-rapidapi-key' : '572ae3c403mshec4f37878016791p15bb99jsn14739a34bc21'
            }
        })
        setBMI(bmiResponse.data)
        console.log(BMI.weight)
    }

    switch(page) {
        case 1:
    return (
        <div>
            <Navbar/>
            <FlexWrapper>
                <Header>Kalkulatory</Header>
                <h6>Nie wiesz jakie jest twoje dzienne zapotrzebowanie kaloryczne?
Dowiedz się teraz korzystając z naszych kalkulatorów! Które
pomogą Ci to określić.</h6>
            </FlexWrapper>
            <ButtonWrapper>
            <CalcButton first onClick={() => setPage(page + 1)}><img src={stIcon}/>Kalkulator BMI</CalcButton>
            <CalcButton onClick={() => window.location = 'https://diety.nfz.gov.pl/local/diet/login.php'}><img src={ndIcon}/>Dzienne spozycie kaloryczne</CalcButton>
            </ButtonWrapper>
           <BottomWrapper> <img className="img" src={bgImg}/> </BottomWrapper>
            
        </div>
    );
case 2:
    return (
        <div>
            <Navbar/>
                <Header>Kalkulator BMI</Header>
                <BMICalc>
                    {/* <h1>response: {BMI}</h1> */}
                    <BmiForm>
                        <input placeholder="Waga.. (kg)" required name="weight" onChange={(e)=> {setWeight(e.target.value)}}/>
                        <input placeholder="Wysokość.. (cm)" required name="height" onChange={(e)=> {setHeight(e.target.value)}}/>
                        <input placeholder="Wiek.." required name="age" onChange={(e)=> {setAge(e.target.value)}}/>
                        <div className="flexx">
                            <div>
                            <input onClick={(e) => {setSex('m')}} type="radio" id="m" name="sex" value="m"
                                    checked/>
                            <label for="m">M</label>
                            </div>

                            <div>
                            <input onClick={(e) => {setSex('f')}} type="radio" id="k" name="sex" value="f"/>
                            <label for="k">K</label>
                            </div>
                        </div>

                    <button className="submitBtn" onClick={submitHandler} value="Zobacz swoje dane">Zobacz swoje dane</button>
                    </BmiForm>
                </BMICalc>

                <BMIData>
                    {/* {BMI} */}
                </BMIData>
        </div>
    )
}}

const BMIData = styled.div`
    color: white;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
`

const BmiForm = styled.form`
    display: flex;
    flex-direction: column;
    width: 100%;
    justify-content: center;
    align-items: center;

    input {
        width: 50%;
        height: 2rem;
        margin: 1rem 1rem;
        background: rgba(255,255,255,.3);
        color: white;
        font-size: 1rem;
        padding: 0.5rem;
    }

    .submitBtn {
        background-color: white;
        color: black;
        width: 10%;
        /* padding: 1rem; */
        font-size: 0.7rem;
        height: 2rem;
    }

    label {
        margin: 1rem 1rem;
    }

    .flexx {
        display: flex;
    }
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

const BMICalc = styled.div`
    display: flex;
    color: white;
`

const CalcButton = styled.button`
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
    width: 20rem;
    height: 7rem;
    margin: 1rem;
    color: white;
    font-size: 2rem;
    background-color: ${props => props.first ? '#00FF88' : '#FFE854'};
    border: 0;

    img {
        width: 30%;
    }
`
const BottomWrapper = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: right;
    align-items: center;
    overflow: hidden;
    width: 100%;
    height: 10rem;

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

const ButtonWrapper = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
`

const FlexWrapper = styled.div`
    display: flex;
    justify-content: space-between;
    color: white;
    align-items: center;
    
    h6 {
        width: 60%;
        font-size: 1.1rem;
        font-weight: normal;
    }
`

export default Calculator;