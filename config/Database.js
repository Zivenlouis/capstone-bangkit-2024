import { Sequelize } from "sequelize";
import mysql2 from 'mysql2';

const db = new Sequelize('auxilium', 'root', '12131415', {
    host: '34.34.222.136',
    dialect: 'mysql',
    dialectModule: mysql2, // Needed to fix sequelize issues with WebPack
});

export default db;