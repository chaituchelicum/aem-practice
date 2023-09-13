import { Model } from '@adobe/aem-spa-page-model-manager';
interface ComponentProps {
    pagePath?: string;
    itemPath?: string;
    cqPath?: string;
    /**
     * Should the component data be retrieved from the aem page model
     * and passed down as props on componentMount
     */
    injectPropsOnInit?: boolean;
}
/**
 * Helper functions for interacting with the AEM environment.
 */
declare const Utils: {
    /**
     * Is the app used in the context of the AEM Page editor.
     * @deprecated use AuthoringUtils.isInEditor from aem-spa-page-model-manager
     */
    isInEditor(): boolean;
    /**
     * Transforms the item data to component properties.
     * It will replace ':' with 'cq' and convert the name to CameCase.
     *
     * @private
     * @param item - the item data
     * @returns the transformed data
     */
    modelToProps(item: Model): any;
    /**
     * Determines the cqPath of a component given its props
     *
     * @private
     * @returns cqPath of the component
     */
    getCQPath(componentProps: ComponentProps): string;
};
export default Utils;
//# sourceMappingURL=Utils.d.ts.map