import React from "react";

function ProgressChart(props) {

    const FONT_SIZE = props.width / 50;
    const maxXFromData = Math.max(...props.data.map((e) => e.x));
    const maxYFromData = Math.max(...props.data.map((e) => e.y));

    const digits = parseFloat(maxYFromData.toString).toFixed(props.precision).length + 1;

    const padding = (FONT_SIZE + digits) * 3;
    const chartWidth = props.width - padding * 2;
    const chartHeight = props.height - padding * 2;

    const Axis = ({ points })  => (
        <polyline fill ="none" stroke="#cccccc" points={points} strokeWidth="0.5"/>
    );

    const XAxis = ()  => (
        <Axis points = {`${padding},${props.height - padding} ${props.width - padding}, ${props.height - padding}`}/>
    );

    const points = props.data.map(e => {
      const x = (e.x / maxXFromData) * chartWidth + padding;
      const y =chartHeight - (e.y / maxYFromData) * chartHeight + padding;
      return `${x},${y}`;
    })
    .join(' ');

    const HorizontalGuides = () => {
        const startX = padding;
        const endX = props.width - padding;

        return new Array(props.horizontalGuides).fill(0).map((_, index) => {
          const ratio = (index + 1) / props.horizontalGuides;
          const yCoordinate = chartHeight - chartHeight * ratio + padding;
          return (
            <polyline fill="none" stroke={'#cccccc'} strokeWidth=".5" points={`${startX},${yCoordinate} ${endX},${yCoordinate}`}/>
            );
        });
    };

    const LabelsXAxis = () => {
        const y = props.height - padding + FONT_SIZE * 2;
        return props.data.map(element => {
          const x =
            (element.x / maxXFromData) * chartWidth + padding - FONT_SIZE / 2;
          return (
            <text x={x} y={y} style={{ fill: '#000000', fontSize: FONT_SIZE, fontFamily: 'Sen' }}>
              {element.label}
            </text>
            );
        });
    };

    const LabelsYAxis = () => {

        const PARTS = props.horizontalGuides;
        return new Array(PARTS + 1).fill(0).map((_, index) => {
          const x = FONT_SIZE;
          const ratio = index / props.horizontalGuides;
          const yCoordinate = chartHeight - chartHeight * ratio + padding + FONT_SIZE / 2;
          return (
            <text  x={x} y={yCoordinate} style={{ fill: '#000000', fontSize: FONT_SIZE, fontFamily: 'Sen' }}>
              {parseFloat(maxYFromData * (index / PARTS)).toFixed(props.precision)}
            </text>
          );
        });
    };

    return (
        <svg viewBox = {`0 0 ${props.width} ${props.height}`}>
            <XAxis/>
            <LabelsXAxis/>
            <LabelsYAxis/>
            <HorizontalGuides/>
            <polyline className="progress-line" fill="none" stroke={props.color} strokeWidth="4.0" points = {points} strokeLinejoin="round" strokeLinecap="round" />
        </svg>
    );

}

export default ProgressChart;