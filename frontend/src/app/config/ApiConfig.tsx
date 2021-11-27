import { Configuration, ConfigurationParameters } from "../../generated";

const configurationParmeter: ConfigurationParameters = {
    basePath: "/api"
}

export const API_CONFIGURATION : Configuration = new Configuration(configurationParmeter);