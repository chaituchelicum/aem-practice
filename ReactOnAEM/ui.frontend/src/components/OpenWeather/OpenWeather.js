import React, { useState,useEffect } from 'react';
import {MapTo} from '@adobe/aem-react-editable-components';
import axios from 'axios';
// Open weather API Key
// For simplicity it is hard coded in the file, ideally this is extracted in to an environment variable
const API_KEY = 'c474ccf4227fa4f7368a334506129897';

// Logic to render placeholder or component
const OpenWeatherEditConfig = {

    emptyLabel: 'Weather',
    isEmpty: function(props) {
        return !props || !props.lat || !props.lon || !props.label;
    }
};

// Wrapper function that includes react-open-weather component
//https://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=0b2f3e7edd1c616a74810d45e9d659e6&units=metric&mode=html
function ReactWeatherWrapper(props){
const { label } = props;
const [weather, setWeather] = useState(null);

useEffect(() => {
    // Fetch weather data from API
    const fetchData = async () => {
      try {
        const response = await axios.get(
          `https://api.openweathermap.org/data/2.5/weather?q=${label}&APPID=${API_KEY}&units=metric`
        );
        setWeather(response.data);
      } catch (error) {
        console.log(error);
      }
    };
  
    fetchData();
  }, [label]);
  

    return (
        <div className="cmp-open-weather">
            <h1>Weather</h1>
            { (weather !== undefined && weather !=null) ?
            <div>
            <h3 style={{color:'red'}}>{weather.name}</h3> 
            <p>Temperature : {weather.main.temp} Celcius <img src={`https://openweathermap.org/img/w/${weather.weather[0].icon}.png`} alt='weathericon'/></p>
            <p>Humidity : {weather.main.humidity}</p>
            <p>Pressure : {weather.main.pressure}</p>
            </div>
            : <p>Weather is not available</p>}
        </div>
    );
}

export default function OpenWeather(props) {

        // render nothing if component not configured
        if (OpenWeatherEditConfig.isEmpty(props)) {
            return null;
        }

        // render ReactWeather component if component configured
        // pass props to ReactWeatherWrapper. These props include the mapped properties from AEM JSON
        return ReactWeatherWrapper(props);

}

// Map OpenWeather to AEM component
MapTo('ReactOnAEM/components/openweather')(OpenWeather , OpenWeatherEditConfig);
